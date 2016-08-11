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

package lameonj.encoder.blade;

import lame.blade.BE_CONFIG.STRUCT_FORMAT.STRUCT_LHV1;
import lame.blade.BladeMP3EncDLL;

/**
 * The <code>BeConfigLHV1</code> is the interface implemented by the 
 * {@link lame.blade.BE_CONFIG} internal class wrapper with
 * the LHV1 mode (LAME).
 * <br /> 
 * Access the internal BE_CONFIG structure publishing the <code>LHV1</code>
 * attribute (LHV1/LAME mode). This is the recommended LAME mode.
 * <br />
 * The current implementation automatically sets:
 * <ul>
 *  <li>
 *      <code>dwConfig</code> to the {@link BladeMP3EncDLL#BE_CONFIG_LAME} value.
 *  </li>
 *  <li>
 *      <code>dwStructVersion</code> to the {@link BladeMP3EncDLL#CURRENT_STRUCT_VERSION} value.
 *  </li>
 *  <li>
 *      <code>dwStructSize</code> to the {@link BladeMP3EncDLL#CURRENT_STRUCT_SIZE} value.
 *  </li>
 * </ul>
 * 
 * @see LAMEOnJBlade#newBeConfigLHV1()
 */
public interface BeConfigLHV1 extends BeConfig
{ 
    /**
     * Returns the internal value of the 
     * {@link lame.blade.BE_CONFIG.STRUCT_FORMAT#LHV1} attribute  
     * of the internal {@link lame.blade.BE_CONFIG} structure.
     *
     * @return the <code>LHV1</code> attribute value.
     */        
    public STRUCT_LHV1 getLHV1();    

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().getDwStructVersion();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#getDwStructVersion()
     */    
    public int getDwStructVersion();
    
    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().getDwStructSize();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#getDwStructSize()
     */   
    public int getDwStructSize();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().getDwSampleRate();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#getDwSampleRate()
     */    
    public int getDwSampleRate();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setDwSampleRate(dwSampleRate);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setDwSampleRate(int)
     */   
    public void setDwSampleRate(int dwSampleRate);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().getDwReSampleRate();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#getDwReSampleRate()
     */     
    public int getDwReSampleRate();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setDwReSampleRate(dwReSampleRate);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setDwReSampleRate(int)
     */
    public void setDwReSampleRate(int dwReSampleRate);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().getNMode();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#getNMode()
     */    
    public int getNMode();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setNMode(nMode);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setNMode(int)
     */   
    public void setNMode(int nMode);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().getDwBitrate();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#getDwBitrate()
     */     
    public int getDwBitrate();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setDwBitrate(dwBitrate);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setDwBitrate(int)
     */    
    public void setDwBitrate(int dwBitrate);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().getDwMaxBitrate();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#getDwMaxBitrate()
     */     
    public int getDwMaxBitrate();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setDwMaxBitrate(dwMaxBitrate);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setDwMaxBitrate(int)
     */     
    public void setDwMaxBitrate(int dwMaxBitrate);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().getNPreset();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#getNPreset()
     */    
    public int getNPreset();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setNPreset(int);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setNPreset(int)
     */    
    public void setNPreset(int nPreset);
    
    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().getDwMpegVersion();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#getDwMpegVersion()
     */     
    public int getDwMpegVersion();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setDwMpegVersion(dwMpegVersion);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setDwMpegVersion(int)
     */    
    public void setDwMpegVersion(int dwMpegVersion);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().getDwPsyModel();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#getDwPsyModel()
     */    
    public int getDwPsyModel();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setDwPsyModel(dwPsyModel);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setDwPsyModel(int)
     */      
    public void setDwPsyModel(int dwPsyModel);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().getDwEmphasis();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#getDwEmphasis()
     */     
    public int getDwEmphasis();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setDwEmphasis(dwEmphasis);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setDwEmphasis(int)
     */    
    public void setDwEmphasis(int dwEmphasis);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().isBPrivate();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#isBPrivate()
     */     
    public boolean isBPrivate();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setBPrivate(bPrivate);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setBPrivate(boolean)
     */    
    public void setBPrivate(boolean bPrivate);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().isBCRC();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#isBCRC()
     */     
    public boolean isBCRC();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setBCRC(bCRC);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setBCRC(boolean)
     */      
    public void setBCRC(boolean bCRC);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().isBCopyright();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#isBCopyright()
     */    
    public boolean isBCopyright();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setBCopyright(bCopyright);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setBCopyright(boolean)
     */     
    public void setBCopyright(boolean bCopyright);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().isBOriginal();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#isBOriginal()
     */    
    public boolean isBOriginal();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setBOriginal(bOriginal);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setBOriginal(boolean)
     */     
    public void setBOriginal(boolean bOriginal);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().isBWriteVBRHeader();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#isBWriteVBRHeader()
     */    
    public boolean isBWriteVBRHeader();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setBWriteVBRHeader(bWriteVBRHeader);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setBWriteVBRHeader(boolean)
     */       
    public void setBWriteVBRHeader(boolean bWriteVBRHeader);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().isBEnableVBR();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#isBEnableVBR()
     */     
    public boolean isBEnableVBR();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setBEnableVBR(bEnableVBR);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setBEnableVBR(boolean)
     */      
    public void setBEnableVBR(boolean bEnableVBR);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().getNVBRQuality();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#getNVBRQuality()
     */    
    public int getNVBRQuality();
    
    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setNVBRQuality(nVBRQuality);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setNVBRQuality(int)
     */    
    public void setNVBRQuality(int nVBRQuality);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().getDwVbrAbr_bps();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#getDwVbrAbr_bps()
     */    
    public int getDwVbrAbr_bps();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setDwVbrAbr_bps(dwVbrAbr_bps);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setDwVbrAbr_bps(int)
     */    
    public void setDwVbrAbr_bps(int dwVbrAbr_bps);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().getNVbrMethod();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#getNVbrMethod()
     */    
    public int getNVbrMethod();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setNVbrMethod(nVbrMethod);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setNVbrMethod(int)
     */    
    public void setNVbrMethod(int nVbrMethod);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().isBNoRes();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#isBNoRes()
     */      
    public boolean isBNoRes();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setBNoRes(bNoRes);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setBNoRes(boolean)
     */       
    public void setBNoRes(boolean bNoRes);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().isBStrictIso();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#isBStrictIso()
     */    
    public boolean isBStrictIso();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setBStrictIso(bStrictIso);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setBStrictIso(boolean)
     */     
    public void setBStrictIso(boolean bStrictIso);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().getNQuality();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#getNQuality()
     */     
    public short getNQuality();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setNQuality(nQuality);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setNQuality(short)
     */    
    public void setNQuality(short nQuality);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getLHV1().getBtReserved();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#getBtReserved()
     */     
    public byte[] getBtReserved();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getLHV1().setBtReserved(btReserved);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_LHV1#setBtReserved(byte[])
     */      
    public void setBtReserved(byte[] btReserved);
}
