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

import lame.blade.BE_VERSION;

/**
 * The <code>BeVersion</code> is the interface implemented by the internal
 * class wrapping the {@link BE_VERSION} structure.
 * <br />
 *
 * @see LAMEOnJBlade#getVersion()
 */
public interface BeVersion
{
    /**
     * Returns the internal {@link BE_VERSION} object used.
     *
     * @return the internal BE_VERSION object used.
     */    
    public BE_VERSION getBE_VERSION(); 
    
    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getBE_VERSION().getByDLLMajorVersion();
     *  </code>
     * </blockquote>
     *
     * @see BE_VERSION#getByDLLMajorVersion()
     */      
    public byte getByDLLMajorVersion();
 
    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getBE_VERSION().getByDLLMinorVersion();
     *  </code>
     * </blockquote>
     *
     * @see BE_VERSION#getByDLLMinorVersion()
     */    
    public byte getByDLLMinorVersion();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getBE_VERSION().getByMajorVersion();
     *  </code>
     * </blockquote>
     *
     * @see BE_VERSION#getByMajorVersion()
     */     
    public byte getByMajorVersion();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getBE_VERSION().getByMinorVersion();
     *  </code>
     * </blockquote>
     *
     * @see BE_VERSION#getByMinorVersion()
     */       
    public byte getByMinorVersion();
    
    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getBE_VERSION().getByDay();
     *  </code>
     * </blockquote>
     *
     * @see BE_VERSION#getByDay()
     */    
    public byte getByDay();
  
    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getBE_VERSION().getByMonth();
     *  </code>
     * </blockquote>
     *
     * @see BE_VERSION#getByMonth()
     */     
    public byte getByMonth();
 
    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getBE_VERSION().getWYear();
     *  </code>
     * </blockquote>
     *
     * @see BE_VERSION#getWYear()
     */    
    public short getWYear();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getBE_VERSION().getZHomepageAsString();
     *  </code>
     * </blockquote>
     *
     * @see BE_VERSION#getZHomepageAsString()
     */     
    public String getZHomepageAsString();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getBE_VERSION().getByAlphaLevel();
     *  </code>
     * </blockquote>
     *
     * @see BE_VERSION#getByAlphaLevel()
     */     
    public byte getByAlphaLevel();
  
    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getBE_VERSION().getByBetaLevel();
     *  </code>
     * </blockquote>
     *
     * @see BE_VERSION#getByBetaLevel()
     */     
    public byte getByBetaLevel();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getBE_VERSION().getByMMXEnabled();
     *  </code>
     * </blockquote>
     *
     * @see BE_VERSION#getByMMXEnabled()
     */    
    public byte getByMMXEnabled();
    
    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getBE_VERSION().getBtReserved();
     *  </code>
     * </blockquote>
     *
     * @see BE_VERSION#getBtReserved()
     */    
    public byte[] getBtReserved();
}
