/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.lameonj.decoder;

import lame.std.Lame;
import lame.std.mp3data_struct;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author jmarranz
 */
public class TestDecoderPlainAPI 
{
    public static void test(String mp3File,String wavFile) throws Exception
    {   
        InputStream input = new BufferedInputStream(new FileInputStream(mp3File));
        OutputStream output = new BufferedOutputStream(new FileOutputStream(wavFile));

        int res = Lame.lame_decode_init();
        checkRes(res);

        mp3data_struct mp3data = decodeHeaders(input);

        writeWAVHeader(mp3data,output);
        
        short[] pcm_l = new short[1152];
        short[] pcm_r = new short[1152];

        int numChannels = mp3data.getStereo();
        int bytesPerSample = 2;
        int dataSize = 0;
        int samples = decodeFrame(pcm_l,pcm_r,input);
        while(samples > 0)
        {
            if (numChannels == 2)
                writePCMFrameStereo(samples,pcm_l,pcm_r,output);
            else
                writePCMFrameMono(samples,pcm_l,output);
            dataSize += samples * bytesPerSample * numChannels;
            samples = decodeFrame(pcm_l,pcm_r,input);
        }

        input.close();
        output.close();

        Lame.lame_decode_exit();
    }

    public static mp3data_struct decodeHeaders(InputStream input) throws Exception
    {
        // http://www.sfr-fresh.com/unix/privat/transcode-1.0.6.tar.gz:a/transcode-1.0.6/import/mpg123.c
        
        mp3data_struct mp3data = new mp3data_struct();
        byte[] buf = new byte[100]; // Must be small

        int res;
        do
        {
            int len = input.read(buf);
            if (len == -1) throw new RuntimeException("Unexpected end of file");
            res = Lame.lame_decode1_headers(buf, len,(short[])null,null, mp3data);
            checkRes(res);
        }        
        while(mp3data.getHeader_parsed() == 0);
            
        // res must be 0
        if (res > 0) throw new RuntimeException("Unexpected frames processed");

        return mp3data;
    }

    public static int decodeFrame(short[] pcm_l,short[] pcm_r,InputStream input) throws Exception
    {
        // http://www.sfr-fresh.com/unix/privat/transcode-1.0.6.tar.gz:a/transcode-1.0.6/import/mpg123.c       
        
        // Ever returns 1152, the number of samples per frame of PCM 16 bits.
        // Note: if mono, only the left channel/buffer is written.
        if (pcm_l.length < 1152) throw new RuntimeException("Expected 1152 (samples per frame) or bigger the size of the buffer left");
        if (pcm_r.length < 1152) throw new RuntimeException("Expected 1152 (samples per frame) or bigger the size of the buffer right");

        byte[] buf = new byte[1024*10]; // (1024 would be enough)

        // Processing pending data before reading new data
        int samples = Lame.lame_decode1(buf, 0, pcm_l, pcm_r);       
        if (samples > 0) return samples;

        int len = input.read(buf);
        if (len == -1) return -1; // No more data

        samples = Lame.lame_decode1(buf, len, pcm_l, pcm_r);
        checkRes(samples);
        if (samples > 0) return samples;
        // samples = 0, needs more data

        while(true)
        {
            len = input.read(buf);
            if (len == -1)
            {
                // End of file. Processing pending data
                samples = Lame.lame_decode1(buf, 0, pcm_l, pcm_r);
                checkRes(samples);
                return samples;
            }
            else
            {
                samples = Lame.lame_decode1(buf, len, pcm_l, pcm_r);
                checkRes(samples);
                if (samples > 0) return samples;
            }
            // samples = 0, needs more data
        }
    }

    public static void writeWAVHeader(mp3data_struct mp3data,OutputStream output) throws IOException
    {
        // http://www.thisisnotalabel.com/How-to-Read-and-Write-WAV-Files---in-C-and-VB.php
        // http://ccrma.stanford.edu/courses/422/projects/WaveFormat/
        
        int numFrames = mp3data.getTotalframes();
        if (numFrames <= 0) throw new RuntimeException("Only MP3 files with Xing header are supported");
        int numChannels = mp3data.getStereo();
        int bytesPerSample = 2;
        int dataSize = numFrames * mp3data.getFramesize() * bytesPerSample * numChannels;

        writeString("RIFF",output);
        writeUInt(dataSize + 36,output); 
        writeString("WAVE",output);
        writeString("fmt ",output);
        writeUInt(16,output); // 16=PCM
        writeUShort(1,output); // uncompressed
        writeUShort(numChannels,output);
        int sampleRate = mp3data.getSamplerate();
        writeUInt(sampleRate,output);
        int bitsPerSample = 16;
        writeUInt(sampleRate * numChannels * bitsPerSample / 8,output);
        writeUShort(numChannels * bitsPerSample / 8,output); // Block Alignment
        writeUShort(bitsPerSample,output);
        writeString("data",output);
        writeUInt(dataSize,output); // Data size.
    }

    public static void writePCMFrameStereo(int samples,short[] pcm_l,short[] pcm_r,OutputStream output) throws IOException
    {
        for(int i = 0; i < samples; i++)
        {
            int left = toUnsigned(pcm_l[i]);
            writeUShort(left,output);
            int right = toUnsigned(pcm_r[i]);            
            writeUShort(right,output);
        }
    }

    public static void writePCMFrameMono(int samples,short[] pcm_l,OutputStream output) throws IOException
    {
        for(int i = 0; i < samples; i++)
        {
            int left = toUnsigned(pcm_l[i]);
            writeUShort(left,output);
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
    
    public static void checkRes(int res)
    {
        if (res == -1) throw new RuntimeException("Failed");
    }    
}
