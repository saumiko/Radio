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

import lame.blade.BE_VERSION;
import lameonj.encoder.blade.BeVersion;


public class BeVersionImpl implements BeVersion
{           
    protected BE_VERSION version;
    
    /** Creates a new instance of BeVersionImpl */
    public BeVersionImpl(BE_VERSION version)
    {
        this.version = version;
    }
    
    public BE_VERSION getBE_VERSION()
    {
        return version;
    }

    public String getZHomepageAsString()
    {
        return getBE_VERSION().getZHomepageAsString();
    }

    public short getWYear()
    {
        return getBE_VERSION().getWYear();        
    }

    public byte getByMonth()
    {
        return getBE_VERSION().getByMonth();        
    }

    public byte getByMinorVersion()
    {
        return getBE_VERSION().getByMinorVersion();         
    }

    public byte getByMajorVersion()
    {
        return getBE_VERSION().getByMajorVersion();        
    }

    public byte getByMMXEnabled()
    {
        return getBE_VERSION().getByMMXEnabled();        
    }

    public byte getByDay()
    {
        return getBE_VERSION().getByDay();         
    }

    public byte getByDLLMinorVersion()
    {
        return getBE_VERSION().getByDLLMinorVersion();        
    }

    public byte getByDLLMajorVersion()
    {
        return getBE_VERSION().getByDLLMajorVersion();        
    }

    public byte getByBetaLevel()
    {
        return getBE_VERSION().getByBetaLevel();         
    }

    public byte getByAlphaLevel()
    {
        return getBE_VERSION().getByAlphaLevel();        
    }

    public byte[] getBtReserved()
    {
        return getBE_VERSION().getBtReserved();        
    }
}
