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

import lame.blade.BE_CONFIG.STRUCT_FORMAT.STRUCT_AAC;
import lameonj.encoder.blade.BeConfigAAC;

public class BeConfigAACImpl extends BeConfigImpl implements BeConfigAAC
{
    
    /**
     * Creates a new instance of BeConfigAACImpl
     */
    public BeConfigAACImpl()
    {
        // config.setDwConfig(BladeMP3EncDLL.?????);          
    }
    
    public STRUCT_AAC getAac()    
    {
        return config.getFormat().getAac();
    }    
    
    public int getDwSampleRate()
    {
        return getAac().getDwSampleRate();
    }

    public void setDwSampleRate(int dwSampleRate)
    {
        getAac().setDwSampleRate(dwSampleRate);
    }

    public byte getByMode()
    {
        return getAac().getByMode();
    }

    public void setByMode(byte byMode)
    {
        getAac().setByMode(byMode);
    }

    public short getWBitrate()
    {
        return getAac().getWBitrate();
    }

    public void setWBitrate(short wBitrate)
    {
        getAac().setWBitrate(wBitrate);
    }

    public byte getByEncodingMethod()
    {
        return getAac().getByEncodingMethod();
    }

    public void setByEncodingMethod(byte byEncodingMethod)
    {
        getAac().setByEncodingMethod(byEncodingMethod);
    }    
}
