/*
 * LAMEOnJ Java based API for LAME MP3 encoder/decoder
 *
 * Copyright (c) 2006-2008 Jose Maria Arranz
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA  02111-1307, USA.
 */

package lameonj.impl.decoder.std;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import lame.std.Lame;
import lameonj.LAMEOnJException;
import lameonj.decoder.std.Decoder;
import lame.std.mp3data_struct;
import lameonj.decoder.std.PCMFrame;

/**
 *
 * @author jmarranz
 */
public abstract class DecoderImpl implements Decoder
{
    protected LAMEOnJStdDecoderImpl parent;
    protected boolean closed = false;
    protected mp3data_struct mp3data = new mp3data_struct();
    protected int numFrames; // Número de frames decodificados devueltos
       
    
    public DecoderImpl(LAMEOnJStdDecoderImpl parent)
    {      
        this.parent = parent;

        parent.setDecoderBlocked(true);

        int res = Lame.lame_decode_init();
        if (res == -1) throw new LAMEOnJException("LAME decoder cannot be initialized");
    } 
    
    public void incNumFrames()
    {
        numFrames++;
    }

    public void checkClosed()
    {
        if (closed) throw new LAMEOnJException("Decoder Task is closed, create a new one");
    }
    
    public mp3data_struct getMP3DataStruct()
    {
        if (mp3data.getHeader_parsed() == 0) return null;
        return mp3data;
    }    
    
    public mp3data_struct getMP3DataStructNotNull()
    {
        mp3data_struct mp3data = getMP3DataStruct();
        if (mp3data == null) throw new LAMEOnJException("Decode MP3 headers first");
        return mp3data;
    }        
    
    public boolean isClosed()
    {
        return closed;
    }

    public void close()
    {
        if (closed) return;

        Lame.lame_decode_exit();

        this.closed = true;

        parent.setDecoderBlocked(false);
    }

    public int getChannelFrameSize()
    {
        // 1152 es el número de samples por frame de PCM 16 bits
        return 1152;
    }    
    
    public int getPCMDataSize()
    {
        return getPCMDataSize(this.numFrames);
    }
    
    public int getPCMDataSize(int numFrames)
    {
        mp3data_struct mp3data = getMP3DataStructNotNull();
        
        int numChannels = mp3data.getStereo(); 
        int bytesPerSample = 2;        
        return numFrames * mp3data.getFramesize() * bytesPerSample * numChannels;
    }

    public boolean isMP3HeaderComplete()
    {
        mp3data_struct mp3data = getMP3DataStructNotNull();
        
        int numFrames = mp3data.getTotalframes();
        return (numFrames > 0); // Only MP3 files with VBR Xing header saves totalFrames
    }

    public mp3data_struct decodeHeadersBase(byte[] mp3buf,int size)
    {
        checkClosed();
        if (size <= 0) throw new LAMEOnJException("MP3 buffer cannot be empty");

        // http://www.sfr-fresh.com/unix/privat/transcode-1.0.6.tar.gz:a/transcode-1.0.6/import/mpg123.c

        // Con un buffer de 500 bytes hay que llamar unas 10 veces para processar los headers

        int res = Lame.lame_decode1_headers(mp3buf,size,(short[])null,(short[])null, mp3data);
        checkFailed(res); 

        // He comprobado que res nunca es > 0 aunque mp3buf sea muy grande, quizás porque hemos
        // pasado buffers de salida nulos (sugiere a LAME que no procese frames),
        // pero por si acaso para dejar claro que no perdemos frames.
        if (res > 0) throw new RuntimeException("Unexpected audio frames processed, reduce the size of the MP3 buffer (100 may be enough)");

        if (mp3data.getHeader_parsed() == 0) return null;

        return mp3data;
    }    
    
    public PCMFrame decodePendingFrameBase()
    {
        checkClosed();
        getMP3DataStructNotNull(); // checks if headers are processed

        // Procesamos los datos pendientes de codificar salvados internamente
        // por LAME
        short[] pcmL = new short[getChannelFrameSize()];
        short[] pcmR = new short[getChannelFrameSize()];

        int samples = Lame.lame_decode1(null,0,pcmL, pcmR);
        checkFailed(samples); 
        if (samples == 0) return null; // Se necesitan más datos
        pcmL = correctSize(samples,pcmL);
        pcmR = correctSize(samples,pcmR);

        return new PCMFrameImpl(pcmL,pcmR,this);
    }    
    
    protected PCMFrame decodeFrameFromBuffer(byte[] mp3buf,int size)
    {
        if (size <= 0) throw new LAMEOnJException("MP3 buffer cannot be empty");

        short[] pcmL = new short[getChannelFrameSize()];
        short[] pcmR = new short[getChannelFrameSize()];

        int samples = Lame.lame_decode1(mp3buf,size,pcmL,pcmR);
        checkFailed(samples); 
        if (samples == 0) return null; // Need more data
        pcmL = correctSize(samples,pcmL);
        pcmR = correctSize(samples,pcmR);

        return new PCMFrameImpl(pcmL,pcmR,this);
    }      
    
    protected static short[] correctSize(int samples,short[] pcm)
    {
        if (samples < pcm.length)
        {
            // Sería el caso de PCM de 8 bits (no probado)
            // Tenemos que devolver los buffers con el tamaño exacto
            short[] pcm2 = new short[samples];
            System.arraycopy(pcm, 0, pcm2, 0, samples);
            return pcm2;
        }
        else return pcm;
    }    
    
    public void writeWAVHeader(OutputStream output)
    {
        // http://www.thisisnotalabel.com/How-to-Read-and-Write-WAV-Files---in-C-and-VB.php
        // http://ccrma.stanford.edu/courses/422/projects/WaveFormat/

        mp3data_struct mp3data = getMP3DataStructNotNull();

        int numFrames = mp3data.getTotalframes(); // 0 if Xing header is not present => incomplete WAV header
        int pcmDataSize = getPCMDataSize(numFrames);

        try
        {
            writeString("RIFF",output);
            writeUInt(pcmDataSize + 36,output);
            writeString("WAVE",output);
            writeString("fmt ",output);
            writeUInt(16,output); // 16=PCM
            writeUShort(1,output); // uncompressed
            int numChannels = mp3data.getStereo();            
            writeUShort(numChannels,output);
            int sampleRate = mp3data.getSamplerate();
            writeUInt(sampleRate,output);
            int bitsPerSample = 16;
            writeUInt(sampleRate * numChannels * bitsPerSample / 8,output);
            writeUShort(numChannels * bitsPerSample / 8,output); // Block Alignment
            writeUShort(bitsPerSample,output);
            writeString("data",output);
            writeUInt(pcmDataSize,output); // Data size.
        }
        catch(IOException ex)
        {
            throw new LAMEOnJException(ex);
        }
    }    
    
    public void writeDecodedFrame(PCMFrame frame,OutputStream output)
    {
        mp3data_struct mp3data = getMP3DataStructNotNull();
        
        short[] pcmL = frame.getPCMDataLeft();
        short[] pcmR = frame.getPCMDataRight();        
        
        int samples = pcmL.length;
        int channels = mp3data.getStereo();
        if (channels == 1)
            writePCMFrameMono(samples,pcmL,output);
        else if (channels == 2)
            writePCMFrameStereo(samples,pcmL,pcmR,output);
        else
            throw new LAMEOnJException("Unsupported number of channels: " + channels);
    }
    
    public void fixWAVHeader(String wavPath)
    {    
        if (!closed) throw new LAMEOnJException("Decoding task is not finished, call close() first");
        
        RandomAccessFile stream = null;     
        try
        {
            stream = new RandomAccessFile(wavPath,"rw");
            int pcmDataSize = getPCMDataSize();
            stream.seek(4);
            stream.write(fromUInt(pcmDataSize + 36));
            stream.seek(44 - 4);
            stream.write(fromUInt(pcmDataSize));
            stream.close();
        }
        catch(IOException ex)
        {
            try
            {
                if (stream != null) stream.close();           
            }
            catch(IOException ex2) { throw new LAMEOnJException(ex2); }
            
            throw new LAMEOnJException(ex);
        }
    }    
    
    public static void writePCMFrameStereo(int samples,short[] pcm_l,short[] pcm_r,OutputStream output)
    {
        try
        {
            for(int i = 0; i < samples; i++)
            {
                int left = toUnsigned(pcm_l[i]);
                writeUShort(left,output);
                int right = toUnsigned(pcm_r[i]);            
                writeUShort(right,output);
            }
        }
        catch(IOException ex)
        {
            throw new LAMEOnJException(ex);
        }        
    }

    public static void writePCMFrameMono(int samples,short[] pcm_l,OutputStream output)
    {
        try
        {
            for(int i = 0; i < samples; i++)
            {
                int left = toUnsigned(pcm_l[i]);
                writeUShort(left,output);
            }
        }
        catch(IOException ex)
        {
            throw new LAMEOnJException(ex);
        }             
    }            
    
    public static void writeString(String name,OutputStream stream) throws IOException
    {
        byte[] buff = name.getBytes("US-ASCII");
        stream.write(buff);
    }

    public static void writeUInt(long value,OutputStream stream) throws IOException
    {
        stream.write(fromUInt(value)); // 4 byte
    }

    public static void writeUShort(int value,OutputStream stream) throws IOException
    {
        stream.write(fromUShort(value)); // 2 byte
    }

    public static byte[] fromUInt(long num)
    {
        if ((num >>> 32) != 0) throw new RuntimeException("Bad number, too big or negative");

        byte[] buff = new byte[4];
        buff[0] = (byte) (num & 0x000000FF);
        buff[1] = (byte)((num & 0x0000FF00) >>> 8);
        buff[2] = (byte)((num & 0x00FF0000) >>> 16);
        buff[3] = (byte)((num & 0xFF000000) >>> 24);
        return buff;
    }

    public static byte[] fromUShort(int num)
    {
        if ((num >>> 16) != 0) throw new RuntimeException("Bad number too big or negative");

        byte[] buff = new byte[2];
        buff[0] = (byte) (num & 0x00FF);
        buff[1] = (byte)((num & 0xFF00) >>> 8);
        return buff;
    }

    public static int toUnsigned(short value)
    {
        return ((int)value) & 0x0000FFFF;
    }
    
    
    public static void checkFailed(int res)
    {
        if (res == -1) throw new LAMEOnJException("Unexpected Error");
    }
}
