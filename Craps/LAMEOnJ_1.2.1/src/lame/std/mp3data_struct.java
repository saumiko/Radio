/*
 * LAMEOnJ Java based API for LAME MP3 encoder
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

package lame.std;

/**
 *
 * <pre>
    typedef struct {
      int header_parsed;   // 1 if header was parsed and following data was computed                                       
      int stereo;          // number of channels                             
      int samplerate;      // sample rate                                    
      int bitrate;         // bitrate                                      
      int mode;            // mp3 frame type                               
      int mode_ext;        // mp3 frame type                               
      int framesize;       // number of samples per mp3 frame              

      // this data is only computed if mpglib detects a Xing VBR header 
      unsigned long nsamp; // number of samples in mp3 file.                 
      int totalframes;     // total number of frames in mp3 file             
      // this data is not currently computed by the mpglib routines 
      int framenum;
    } mp3data_struct;
 * </pre>
 */
public class mp3data_struct
{
      protected int header_parsed;   /* 1 if header was parsed and following data was
                                        computed                                       */
      protected int stereo;          /* number of channels                             */
      protected int samplerate;      /* sample rate                                    */
      protected int bitrate;         /* bitrate                                        */
      protected int mode;            /* mp3 frame type                                 */
      protected int mode_ext;        /* mp3 frame type                                 */
      protected int framesize;       /* number of samples per mp3 frame                */

      /* this data is only computed if mpglib detects a Xing VBR header */
      protected int nsamp; /* number of samples in mp3 file.                 */
      protected int totalframes;     /* total number of frames in mp3 file             */

      /* this data is not currently computed by the mpglib routines */
      protected int framenum;        /* frames decoded counter                         */

    
    /** Creates a new instance of mp3data_struct */
    public mp3data_struct()
    {
    }

    public int getHeader_parsed()
    {
        return header_parsed;
    }

    public void setHeader_parsed(int header_parsed)
    {
        this.header_parsed = header_parsed;
    }

    public int getStereo()
    {
        return stereo;
    }

    public void setStereo(int stereo)
    {
        this.stereo = stereo;
    }

    public int getSamplerate()
    {
        return samplerate;
    }

    public void setSamplerate(int samplerate)
    {
        this.samplerate = samplerate;
    }

    public int getBitrate()
    {
        return bitrate;
    }

    public void setBitrate(int bitrate)
    {
        this.bitrate = bitrate;
    }

    public int getMode()
    {
        return mode;
    }

    public void setMode(int mode)
    {
        this.mode = mode;
    }

    public int getMode_ext()
    {
        return mode_ext;
    }

    public void setMode_ext(int mode_ext)
    {
        this.mode_ext = mode_ext;
    }

    public int getFramesize()
    {
        return framesize;
    }

    public void setFramesize(int framesize)
    {
        this.framesize = framesize;
    }

    public int getNsamp()
    {
        return nsamp;
    }

    public void setNsamp(int nsamp)
    {
        this.nsamp = nsamp;
    }

    public int getTotalframes()
    {
        return totalframes;
    }

    public void setTotalframes(int totalframes)
    {
        this.totalframes = totalframes;
    }

    public int getFramenum()
    {
        return framenum;
    }

    public void setFramenum(int framenum)
    {
        this.framenum = framenum;
    }
    
}
