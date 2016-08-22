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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import lame.std.mp3data_struct;
import lameonj.LAMEOnJException;
import lameonj.decoder.std.PCMFrame;
import lameonj.decoder.std.StreamDecoder;

/**
 *
 * @author jmarranz
 */
public class StreamDecoderImpl extends DecoderImpl implements StreamDecoder
{
    protected BufferedInputStream mp3Stream;
    protected boolean encodingTaskInProcess = false; 
    
    /**
     * Creates a new instance of StreamDecoderImpl
     */
    public StreamDecoderImpl(String mp3File,LAMEOnJStdDecoderImpl std)
    {
        this(createFileInputStream(mp3File),std);
    }

    public StreamDecoderImpl(InputStream input,LAMEOnJStdDecoderImpl std)
    {
        super(std);

        if (!(input instanceof BufferedInputStream))
            input = new BufferedInputStream(input);

        this.mp3Stream = (BufferedInputStream)input;
    }

    public static FileInputStream createFileInputStream(String sourceFile)
    {
        try
        {
            return new FileInputStream(sourceFile);
        }
        catch(IOException ex)
        {
            throw new LAMEOnJException(ex);
        }
    }

    public void initDecodingTask()
    {
        checkClosed();
        // Evitamos así que una codificación progresiva se mezcle con una codificación de una sola llamada
        if (encodingTaskInProcess) throw new LAMEOnJException("There is an encoding task in process");

        this.encodingTaskInProcess = true;
    }      
    
    public void close()
    {
        if (closed) return;

        super.close();

        try
        {
            mp3Stream.close();
        }
        catch(IOException ex)
        {
            throw new LAMEOnJException(ex);
        }
    }

    public InputStream getSourceInputStream()
    {
        return mp3Stream;
    }

    public mp3data_struct decodeHeaders()
    {
        initDecodingTask();
        
        try
        {
            byte[] mp3buf = new byte[500];
            mp3data_struct headers;
            do
            {
                int size = mp3Stream.read(mp3buf);
                if (size == 0) throw new RuntimeException("Unexpected end of file");
                headers = decodeHeadersBase(mp3buf,size);
            }
            while(headers == null);

            return headers;
        }
        catch(IOException ex)
        {
            throw new LAMEOnJException(ex);
        }
    }

    public PCMFrame decodeFrame()
    {
        try
        {
            PCMFrame frame = decodePendingFrameBase();
            if (frame == null)
            {
                byte[] mp3buf = new byte[1024];
                do
                {
                    int size = mp3Stream.read(mp3buf);
                    if (size <= 0) // End of file
                    {
                        mp3Stream.close();
                        return decodePendingFrameBase();
                    }

                    frame = decodeFrameFromBuffer(mp3buf,size);
                }
                while(frame == null);

                return frame;
            }
            else return frame;
        }
        catch(IOException ex)
        {
            throw new LAMEOnJException(ex);
        }
    }

    public void decode(String wavFile)
    {
        FileOutputStream wavStream;

        try
        {
            wavStream = new FileOutputStream(wavFile);
        }
        catch(IOException ex)
        {
            throw new LAMEOnJException(ex);
        }

        decode(wavStream);

        if (!isMP3HeaderComplete())
            fixWAVHeader(wavFile);
    }

    public void decode(OutputStream wavStream)
    {
        if (!(wavStream instanceof BufferedOutputStream))
            wavStream = new BufferedOutputStream(wavStream);

        try
        {
            mp3data_struct headers = decodeHeaders();

            writeWAVHeader(wavStream);

            PCMFrame frame;
            do
            {
                frame = decodeFrame();
                if (frame != null)
                    writeDecodedFrame(frame,wavStream);
            }
            while(frame != null);
        }
        finally
        {
            close();
            try
            {
                wavStream.close();
            }
            catch(IOException ex)
            {
                throw new LAMEOnJException(ex);
            }
        }
    }
}
