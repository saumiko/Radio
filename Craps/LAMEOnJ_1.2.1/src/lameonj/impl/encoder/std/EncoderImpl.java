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

import lame.std.FileUtil;
import lame.std.Lame;
import lame.std.lame_global_flags;
import lameonj.LAMEOnJException;
import lameonj.encoder.std.Encoder;
import lameonj.encoder.std.EncoderConfig;

/**
 *
 * @author jmarranz
 */
public abstract class EncoderImpl implements Encoder
{
    protected LAMEOnJStdEncoderImpl std;
    protected lame_global_flags flags;
    protected boolean closed = false;
    protected boolean encodingTaskInProcess = false;
    protected EncoderConfigImpl config = new EncoderConfigImpl(this);

    /**
     * Creates a new instance of EncoderImpl
     */
    public EncoderImpl(LAMEOnJStdEncoderImpl std)
    {
        this.std = std;

        this.flags = Lame.lame_init();
    }

    public EncoderConfig getEncoderConfig()
    {
        return config;
    }

    public lame_global_flags getLameFlags()
    {
        if (closed)
            throw new LAMEOnJException("Encoder is closed"); // flags is not valid

        return flags;
    }

    public boolean isEncodingTaskInProcess()
    {
        return encodingTaskInProcess;
    }

    public boolean isClosed()
    {
        return closed;
    }

    public void close()
    {
        if (closed) return;

        int rc = Lame.lame_close(flags);
        LAMEOnJStdEncoderImpl.checkError(rc);

        closed = true;
    }


    public void initEncodingTask()
    {
        if (closed) throw new LAMEOnJException("This encoder is closed, create a new one");

        if (encodingTaskInProcess) throw new LAMEOnJException("There is an encoding task in process");

        int rc = Lame.lame_init_params(flags);
        LAMEOnJStdEncoderImpl.checkError(rc);

        this.encodingTaskInProcess = true;
    }

    public static int calcSamplesPerFrame(int numChannels)
    {
        // For MPEG-I, 1152 samples per frame per channel (Lame.lame_get_framesize(flags) returns 1152)
        int mSamplesPerFrameAndChannel = 1152;
        int mSamplesPerFrame = 1152 * numChannels;

        return mSamplesPerFrame;
    }

    public static int calcOuputBufferSize(int numChannels)
    {
        int mSamplesPerFrame = calcSamplesPerFrame(numChannels);

        // Worst case MPEG-I (see lame.h/lame_encode_buffer())
        int mOutBufferSize = mSamplesPerFrame * (320 / 8) / 8 + 4 * 1152 * (320 / 8) / 8 + 512;
        // int mOutBufferSize = (int)(1.25 * ( mSamplesPerFrame / numChannels ) + 7200);
        return mOutBufferSize;
    }

    public static int encodeBuffer(lame_global_flags flags,byte[] pPCMBuffer,int size,byte[] pMP3Buffer)
    {
        // Encode samples

        int numChannels = Lame.lame_get_num_channels(flags);

        int readSamplesAllChannels = size / 2; // size is "bytes", each sample is 2 bytes
        int readSamplesPerChannel = readSamplesAllChannels / numChannels;
        int nOutputBytes;
        if (numChannels == 2)
            nOutputBytes = Lame.lame_encode_buffer_interleaved(flags,pPCMBuffer,readSamplesPerChannel,pMP3Buffer,0);
        else
            nOutputBytes = Lame.lame_encode_buffer(flags,pPCMBuffer,pPCMBuffer,readSamplesPerChannel,pMP3Buffer,0);

        return nOutputBytes;
    }

    public int encodeFlush(byte[] pMP3Buffer)
    {
        if (!encodingTaskInProcess) throw new LAMEOnJException("There is no encoding task in process");

        int nOutputBytes = encodeFlushNoGap(flags, pMP3Buffer);

        this.encodingTaskInProcess = false;

        return nOutputBytes;
    }

    public static int encodeFlushNoGap(lame_global_flags flags,byte[] pMP3Buffer)
    {
        return Lame.lame_encode_flush_nogap( flags, pMP3Buffer, 0 );
    }

    public void writeVbrTag(String mp3File)
    {
        if (closed) throw new LAMEOnJException("This encoder is closed, create a new one");

        if (Lame.lame_get_bWriteVbrTag(flags) > 0)
        {
            FileUtil.FILE fpStream = FileUtil.fopen( mp3File, "rb+" );

            Lame.lame_mp3_tags_fid( flags, fpStream );

            FileUtil.fclose( fpStream );
        }
    }


}
