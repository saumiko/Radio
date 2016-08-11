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

import lame.blade.BE_CONFIG.STRUCT_FORMAT.STRUCT_MP3;
import lame.blade.BladeMP3EncDLL;

/**
 * The <code>BeConfigMP3</code> is the interface implemented by the 
 * {@link lame.blade.BE_CONFIG} internal class wrapper with
 * the old MP3 mode.
 * <br /> 
 * Access the internal BE_CONFIG structure publishing the <code>mp3</code>
 * attribute (MP3 mode). According LAME this configuration mode is obsolete
 * (LHV1 must be used instead), but provided by compatibility with old BladeEnc based code.
 * 
 * @see LAMEOnJBlade#newBeConfigMP3()
 */
public interface BeConfigMP3 extends BeConfig
{
    /**
     * Returns the internal value of the 
     * {@link lame.blade.BE_CONFIG.STRUCT_FORMAT#mp3} attribute  
     * of the internal {@link lame.blade.BE_CONFIG} structure.
     *
     * @return the <code>mp3</code> attribute value.
     */     
    public STRUCT_MP3 getMp3();
    
    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getMp3().getDwSampleRate();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_MP3#getDwSampleRate()
     */    
    public int getDwSampleRate();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getMp3().setDwSampleRate(dwSampleRate);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_MP3#setDwSampleRate(int)
     */   
    public void setDwSampleRate(int dwSampleRate);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getMp3().getByMode();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_MP3#getByMode()
     */    
    public byte getByMode();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getMp3().setByMode(byMode);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_MP3#setByMode(byte)
     */    
    public void setByMode(byte byMode);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getMp3().getWBitrate();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_MP3#getWBitrate()
     */     
    public short getWBitrate();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getMp3().setWBitrate(byMode);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_MP3#setWBitrate(short)
     */    
    public void setWBitrate(short wBitrate);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getMp3().isBPrivate();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_MP3#isBPrivate()
     */      
    public boolean isBPrivate();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getMp3().setBPrivate(bPrivate);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_MP3#setBPrivate(boolean)
     */     
    public void setBPrivate(boolean bPrivate);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getMp3().isBCRC();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_MP3#isBCRC()
     */     
    public boolean isBCRC();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getMp3().setBCRC(bCRC);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_MP3#setBCRC(boolean)
     */    
    public void setBCRC(boolean bCRC);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getMp3().isBCopyright();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_MP3#isBCopyright()
     */    
    public boolean isBCopyright();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getMp3().setBCopyright(bCopyright);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_MP3#setBCopyright(boolean)
     */     
    public void setBCopyright(boolean bCopyright);

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      return getMp3().isBOriginal();
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_MP3#isBOriginal()
     */      
    public boolean isBOriginal();

    /**
     * Current implementation calls:
     * <blockquote>
     *  <code>
     *      getMp3().setBOriginal(bOriginal);
     *  </code>
     * </blockquote>
     *
     * @see STRUCT_MP3#setBOriginal(boolean)
     */     
    public void setBOriginal(boolean bOriginal);
}
