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

package lameonj.impl.encoder.std;

import lame.std.Lame;
import lame.std.MPEG_mode;
import lameonj.LAMEOnJException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author jmarranz
 */
public class StreamEncoderWAVImpl extends StreamEncoderImpl
{

    /**
     * Creates a new instance of StreamEncoderWAVImpl
     */
    public StreamEncoderWAVImpl(String sourceFile,LAMEOnJStdEncoderImpl std)
    {
        super(sourceFile,std);
    }

    public StreamEncoderWAVImpl(InputStream input,LAMEOnJStdEncoderImpl std)
    {
        super(input,std);
    }

    public static boolean isWAV(String file)
    {
        FileInputStream stream;
        try
        {
            stream = new FileInputStream(file);
        }
        catch(IOException ex)
        {
            throw new LAMEOnJException(ex);
        }

        try
        {
            return readString("RIFF",stream);
        }
        catch(IOException ex)
        {
            closeStream(stream);
            throw new LAMEOnJException(ex);
        }
    }

    public void parseHeader()
    {
        // http://ccrma.stanford.edu/courses/422/projects/WaveFormat/
        try
        {
            if (!readString("RIFF",sourceStream)) // 4 bytes
                throw new LAMEOnJException("Not a WAV stream");

            long skipped = sourceStream.skip(4); // 4 bytes (ChunkSize)

            if (!readString("WAVE",sourceStream)) // 4 bytes
                throw new LAMEOnJException("Not a WAV stream");

            if (!readString("fmt ",sourceStream)) // 4 bytes
                throw new LAMEOnJException("Not a WAV stream");

            long subchunk1Size = readUInt(sourceStream);  // 4 byte
            if (subchunk1Size != 16)
                    throw new LAMEOnJException("Expected 16=PCM, bad WAV header");

            int audioFormat = readUShort(sourceStream);  // 2 byte
            if (audioFormat != 1)
                    throw new LAMEOnJException("WAV stream is compressed");

            int numChannels = readUShort(sourceStream);  // 2 byte
            if ((numChannels < 1) || (numChannels > 2))
                    throw new LAMEOnJException("Unsupported number of channels: " + numChannels);

            int rc;
            rc = Lame.lame_set_num_channels(flags, numChannels);
            LAMEOnJStdEncoderImpl.checkError(rc);

            int mpegMode;
            if (numChannels == 2) mpegMode = MPEG_mode.JOINT_STEREO;
            else mpegMode = MPEG_mode.MONO;
            rc = Lame.lame_set_mode( flags, mpegMode );
            LAMEOnJStdEncoderImpl.checkError(rc);

            long sampleRate = readUInt(sourceStream);  // 4 byte
            rc = Lame.lame_set_in_samplerate(flags,(int)sampleRate); // Ex 44100
            LAMEOnJStdEncoderImpl.checkError(rc);

            skipped = sourceStream.skip(4); // 4 bytes (ByteRate)

            skipped = sourceStream.skip(2); // 2 bytes (BlockAlign)

            int bitsPerSample = readUShort(sourceStream);  // 2 byte
            if (bitsPerSample != 16)
                throw new LAMEOnJException("Unsupported number of bits per sample (must be 16): " + bitsPerSample);

            if (!readString("data",sourceStream)) // 4 bytes
                throw new LAMEOnJException("Not a WAV stream");

            skipped = sourceStream.skip(4); // 4 bytes (Subchunk2Size)

            // Total header: 44
        }
        catch(IOException ex)
        {
            throw new LAMEOnJException(ex);
        }
    }

    public static int readUShort(InputStream stream) throws IOException
    {
        byte[] buff = new byte[2];
        int read = stream.read(buff); // 2 byte
        return toUShort(buff);
    }

    public static long readUInt(InputStream stream) throws IOException
    {
        byte[] buff = new byte[4];
        int read = stream.read(buff); // 4 byte
        return toUInt(buff);
    }

    public static boolean readString(String name,InputStream stream) throws IOException
    {
        byte[] nameBuff = new byte[name.length()];
        int read = stream.read(nameBuff);
        StringBuffer nameReaded = new StringBuffer();
        for(int i = 0; i < nameBuff.length; i++)
            nameReaded.append((char)nameBuff[i]);

        return nameReaded.toString().equals(name);
    }

    public static int toUShort(byte[] num)
    {
        // big endian format
        if (num.length != 2) throw new LAMEOnJException("Internal Error");

        return (toUInt(num[0]) | (toUInt(num[1]) << 8));
    }

    public static long toUInt(byte[] num)
    {
        // big endian format
        if (num.length != 4) throw new LAMEOnJException("Internal Error");
        return toULong(num[0]) | (toULong(num[1]) << 8) | (toULong(num[2]) << 16) | (toULong(num[3]) << 24);
    }

    public static short toUShort(byte value)
    {
        return (short) (((short)value) & ((short)0x00FF));
    }

    public static int toUInt(byte value)
    {
        return toUShort(value);
    }

    public static long toULong(byte value)
    {
        return toUShort(value);
    }
}
