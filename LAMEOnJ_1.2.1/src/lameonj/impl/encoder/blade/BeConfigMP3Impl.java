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

package lameonj.impl.encoder.blade;

import lame.blade.BE_CONFIG.STRUCT_FORMAT.STRUCT_MP3;
import lame.blade.BladeMP3EncDLL;
import lameonj.encoder.blade.BeConfigMP3;


public class BeConfigMP3Impl extends BeConfigImpl implements BeConfigMP3
{
    
    /**
     * Creates a new instance of BeConfigMP3Impl
     */
    public BeConfigMP3Impl()
    {
        config.setDwConfig(BladeMP3EncDLL.BE_CONFIG_MP3);        
    }
    
    public STRUCT_MP3 getMp3()    
    {
        return config.getFormat().getMp3();
    }
    
    public int getDwSampleRate()
    {
        return getMp3().getDwSampleRate();
    }

    public void setDwSampleRate(int dwSampleRate)
    {
        getMp3().setDwSampleRate(dwSampleRate);
    }

    public byte getByMode()
    {
        return getMp3().getByMode();
    }

    public void setByMode(byte byMode)
    {
        getMp3().setByMode(byMode);
    }

    public short getWBitrate()
    {
        return getMp3().getWBitrate();
    }

    public void setWBitrate(short wBitrate)
    {
        getMp3().setWBitrate(wBitrate);
    }

    public boolean isBPrivate()
    {
        return getMp3().isBPrivate();
    }

    public void setBPrivate(boolean bPrivate)
    {
        getMp3().setBPrivate(bPrivate);
    }

    public boolean isBCRC()
    {
        return getMp3().isBCRC();
    }

    public void setBCRC(boolean bCRC)
    {
        getMp3().setBCRC(bCRC);
    }

    public boolean isBCopyright()
    {
        return getMp3().isBCopyright();
    }

    public void setBCopyright(boolean bCopyright)
    {
        getMp3().setBCopyright(bCopyright);
    }

    public boolean isBOriginal()
    {
        return getMp3().isBOriginal();
    }

    public void setBOriginal(boolean bOriginal)
    {
        getMp3().setBOriginal(bOriginal);
    }    
}
