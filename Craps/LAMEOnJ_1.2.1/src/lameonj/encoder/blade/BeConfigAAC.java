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

import lame.blade.BE_CONFIG.STRUCT_FORMAT.STRUCT_AAC;

/**
 * The <code>BeConfigAAC</code> is the interface implemented by the 
 * {@link lame.blade.BE_CONFIG} internal class wrapper with
 * the AAC mode.
 * <br /> 
 * Access the internal BE_CONFIG structure publishing the <code>aac</code>
 * attribute (AAC mode).
 *
 * @see lameonj.encoder.blade.LAMEOnJBlade#newBeConfigAAC()
 */
public interface BeConfigAAC extends BeConfig
{   
    /**
     * Returns the internal value of the 
     * {@link lame.blade.BE_CONFIG.STRUCT_FORMAT#aac} attribute  
     * of the internal {@link lame.blade.BE_CONFIG} structure.
     *
     * @return the <code>acc</code> attribute value.
     */    
    public STRUCT_AAC getAac();
    
    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getAac().getDwSampleRate();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_AAC#getDwSampleRate()
     */
    public int getDwSampleRate();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getAac().setDwSampleRate(dwSampleRate);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_AAC#setDwSampleRate(int)
     */    
    public void setDwSampleRate(int dwSampleRate);
    
    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getAac().getByMode();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_AAC#getByMode()
     */
    public byte getByMode();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getAac().setByMode(byMode);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_AAC#setByMode(byte)
     */    
    public void setByMode(byte byMode);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getAac().getWBitrate();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_AAC#getWBitrate()
     */
    public short getWBitrate();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getAac().setWBitrate(wBitrate);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_AAC#setWBitrate(short)
     */    
    public void setWBitrate(short wBitrate);
    
    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getAac().getByEncodingMethod();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_AAC#getByEncodingMethod()
     */    
    public byte getByEncodingMethod();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getAac().setByEncodingMethod(byEncodingMethod);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_AAC#setByEncodingMethod(byte)
     */    
    public void setByEncodingMethod(byte byEncodingMethod);
}
