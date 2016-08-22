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

import lame.blade.BE_CONFIG.STRUCT_FORMAT.STRUCT_LHV1;
import lame.blade.BladeMP3EncDLL;
import lameonj.encoder.blade.BeConfigLHV1;

public class BeConfigLAMEImpl extends BeConfigImpl implements BeConfigLHV1
{
    
    /**
     * Creates a new instance of BeConfigLAMEImpl
     */
    public BeConfigLAMEImpl()
    {
        config.setDwConfig(BladeMP3EncDLL.BE_CONFIG_LAME);  
        getLHV1().setDwStructVersion(BladeMP3EncDLL.CURRENT_STRUCT_VERSION);                
        getLHV1().setDwStructSize(BladeMP3EncDLL.CURRENT_STRUCT_SIZE);
    }
    
    public STRUCT_LHV1 getLHV1()    
    {
        return config.getFormat().getLHV1();
    }
    
    public int getDwStructVersion()
    {
        return getLHV1().getDwStructVersion();
    }
/*
    public void setDwStructVersion(int dwStructVersion)
    {
        getLHV1().setDwStructVersion(dwStructVersion);
    }
*/
    public int getDwStructSize()
    {
        return getLHV1().getDwStructSize();
    }
/*
    public void setDwStructSize(int dwStructSize)
    {
        getLHV1().setDwStructSize(dwStructSize);
    }
*/
    public int getDwSampleRate()
    {
        return getLHV1().getDwSampleRate();
    }

    public void setDwSampleRate(int dwSampleRate)
    {
        getLHV1().setDwSampleRate(dwSampleRate);
    }

    public int getDwReSampleRate()
    {
        return getLHV1().getDwReSampleRate();
    }

    public void setDwReSampleRate(int dwReSampleRate)
    {
        getLHV1().setDwReSampleRate(dwReSampleRate);
    }

    public int getNMode()
    {
        return getLHV1().getNMode();
    }

    public void setNMode(int nMode)
    {
        getLHV1().setNMode(nMode);
    }

    public int getDwBitrate()
    {
        return getLHV1().getDwBitrate();
    }

    public void setDwBitrate(int dwBitrate)
    {
        getLHV1().setDwBitrate(dwBitrate);
    }

    public int getDwMaxBitrate()
    {
        return getLHV1().getDwMaxBitrate();
    }

    public void setDwMaxBitrate(int dwMaxBitrate)
    {
        getLHV1().setDwMaxBitrate(dwMaxBitrate);
    }

    public int getNPreset()
    {
        return getLHV1().getNPreset();
    }

    public void setNPreset(int nPreset)
    {
        getLHV1().setNPreset(nPreset);
    }

    public int getDwMpegVersion()
    {
        return getLHV1().getDwMpegVersion();
    }

    public void setDwMpegVersion(int dwMpegVersion)
    {
        getLHV1().setDwMpegVersion(dwMpegVersion);
    }

    public int getDwPsyModel()
    {
        return getLHV1().getDwPsyModel();
    }

    public void setDwPsyModel(int dwPsyModel)
    {
        getLHV1().setDwPsyModel(dwPsyModel);
    }

    public int getDwEmphasis()
    {
        return getLHV1().getDwEmphasis();
    }

    public void setDwEmphasis(int dwEmphasis)
    {
        getLHV1().setDwEmphasis(dwEmphasis);
    }

    public boolean isBPrivate()
    {
        return getLHV1().isBPrivate();
    }

    public void setBPrivate(boolean bPrivate)
    {
        getLHV1().setBPrivate(bPrivate);
    }

    public boolean isBCRC()
    {
        return getLHV1().isBCRC();
    }

    public void setBCRC(boolean bCRC)
    {
        getLHV1().setBCRC(bCRC);
    }

    public boolean isBCopyright()
    {
        return getLHV1().isBCopyright();
    }

    public void setBCopyright(boolean bCopyright)
    {
        getLHV1().setBCopyright(bCopyright);
    }

    public boolean isBOriginal()
    {
        return getLHV1().isBOriginal();
    }

    public void setBOriginal(boolean bOriginal)
    {
        getLHV1().setBOriginal(bOriginal);
    }

    public boolean isBWriteVBRHeader()
    {
        return getLHV1().isBWriteVBRHeader();
    }

    public void setBWriteVBRHeader(boolean bWriteVBRHeader)
    {
        getLHV1().setBWriteVBRHeader(bWriteVBRHeader);
    }

    public boolean isBEnableVBR()
    {
        return getLHV1().isBEnableVBR();
    }

    public void setBEnableVBR(boolean bEnableVBR)
    {
        getLHV1().setBEnableVBR(bEnableVBR);
    }

    public int getNVBRQuality()
    {
        return getLHV1().getNVBRQuality();
    }

    public void setNVBRQuality(int nVBRQuality)
    {
        getLHV1().setNVBRQuality(nVBRQuality);
    }

    public int getDwVbrAbr_bps()
    {
        return getLHV1().getDwVbrAbr_bps();
    }

    public void setDwVbrAbr_bps(int dwVbrAbr_bps)
    {
        getLHV1().setDwVbrAbr_bps(dwVbrAbr_bps);
    }

    public int getNVbrMethod()
    {
        return getLHV1().getNVbrMethod();
    }

    public void setNVbrMethod(int nVbrMethod)
    {
        getLHV1().setNVbrMethod(nVbrMethod);
    }

    public boolean isBNoRes()
    {
        return getLHV1().isBNoRes();
    }

    public void setBNoRes(boolean bNoRes)
    {
        getLHV1().setBNoRes(bNoRes);
    }

    public boolean isBStrictIso()
    {
        return getLHV1().isBStrictIso();
    }

    public void setBStrictIso(boolean bStrictIso)
    {
        getLHV1().setBStrictIso(bStrictIso);
    }

    public short getNQuality()
    {
        return getLHV1().getNQuality();
    }

    public void setNQuality(short nQuality)
    {
        getLHV1().setNQuality(nQuality);
    }

    public byte[] getBtReserved()
    {
        return getLHV1().getBtReserved();
    }

    public void setBtReserved(byte[] btReserved)
    {
        getLHV1().setBtReserved(btReserved);
    }
}
