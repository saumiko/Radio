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
package lame.blade;

/** 
 * The <code>BE_VERSION</code> class is the Java symmetric representation of the corresponding 
 * C LAME structure as declared in the file BladeMP3EncDLL.h. 

<code><pre> 
typedef struct	{

	// BladeEnc DLL Version number

	BYTE	byDLLMajorVersion;
	BYTE	byDLLMinorVersion;

	// BladeEnc Engine Version Number

	BYTE	byMajorVersion;
	BYTE	byMinorVersion;

	// DLL Release date

	BYTE	byDay;
	BYTE	byMonth;
	WORD	wYear;

	// BladeEnc	Homepage URL

	CHAR	zHomepage[BE_MAX_HOMEPAGE + 1];	

	BYTE	byAlphaLevel;
	BYTE	byBetaLevel;
	BYTE	byMMXEnabled;

	BYTE	btReserved[125];


} BE_VERSION, *PBE_VERSION ATTRIBUTE_PACKED;
</pre></code> 
*/
        
public class BE_VERSION
{
    protected byte byDLLMajorVersion;
    protected byte byDLLMinorVersion;
    protected byte byMajorVersion;
    protected byte byMinorVersion;
    protected byte byDay;
    protected byte byMonth;
    protected short wYear;
    protected byte[] zHomepage = new byte[BladeMP3EncDLL.BE_MAX_HOMEPAGE + 1]; // 129, embedded
    protected byte byAlphaLevel;
    protected byte byBetaLevel;
    protected byte byMMXEnabled;
    protected byte[] btReserved = new byte[125]; // embedded
    
    /**
     * Returns the current value of <code>byDLLMajorVersion</code> attribute.
     *
     * @return the value of <code>byDLLMajorVersion</code>
     */    
    public byte getByDLLMajorVersion()
    {
        return byDLLMajorVersion;
    }

    /**
     * Sets the value of the <code>byDLLMajorVersion</code> attribute.
     *
     * @param byDLLMajorVersion the new value
     */     
    public void setByDLLMajorVersion(byte byDLLMajorVersion)
    {
        this.byDLLMajorVersion = byDLLMajorVersion;
    }

    /**
     * Returns the current value of <code>byDLLMinorVersion</code> attribute.
     *
     * @return the value of <code>byDLLMinorVersion</code>
     */     
    public byte getByDLLMinorVersion()
    {
        return byDLLMinorVersion;
    }

    /**
     * Sets the value of the <code>byDLLMinorVersion</code> attribute.
     *
     * @param byDLLMinorVersion the new value
     */    
    public void setByDLLMinorVersion(byte byDLLMinorVersion)
    {
        this.byDLLMinorVersion = byDLLMinorVersion;
    }

    /**
     * Returns the current value of <code>byMajorVersion</code> attribute.
     *
     * @return the value of <code>byMajorVersion</code>
     */      
    public byte getByMajorVersion()
    {
        return byMajorVersion;
    }

    /**
     * Sets the value of the <code>byMajorVersion</code> attribute.
     *
     * @param byMajorVersion the new value
     */    
    public void setByMajorVersion(byte byMajorVersion)
    {
        this.byMajorVersion = byMajorVersion;
    }

    /**
     * Returns the current value of <code>byMinorVersion</code> attribute.
     *
     * @return the value of <code>byMinorVersion</code>
     */       
    public byte getByMinorVersion()
    {
        return byMinorVersion;
    }

    /**
     * Sets the value of the <code>byMinorVersion</code> attribute.
     *
     * @param byMinorVersion the new value
     */    
    public void setByMinorVersion(byte byMinorVersion)
    {
        this.byMinorVersion = byMinorVersion;
    }

    /**
     * Returns the current value of <code>byDay</code> attribute.
     *
     * @return the value of <code>byDay</code>
     */    
    public byte getByDay()
    {
        return byDay;
    }

    /**
     * Sets the value of the <code>byDay</code> attribute.
     *
     * @param byDay the new value
     */        
    public void setByDay(byte byDay)
    {
        this.byDay = byDay;
    }

    /**
     * Returns the current value of <code>byMonth</code> attribute.
     *
     * @return the value of <code>byMonth</code>
     */    
    public byte getByMonth()
    {
        return byMonth;
    }

    /**
     * Sets the value of the <code>byMonth</code> attribute.
     *
     * @param byMonth the new value
     */     
    public void setByMonth(byte byMonth)
    {
        this.byMonth = byMonth;
    }

    /**
     * Returns the current value of <code>wYear</code> attribute.
     *
     * @return the value of <code>wYear</code>
     */        
    public short getWYear()
    {
        return wYear;
    }

    /**
     * Sets the value of the <code>wYear</code> attribute.
     *
     * @param wYear the new value
     */    
    public void setWYear(short wYear)
    {
        this.wYear = wYear;
    }

    /**
     * Returns the current value of <code>zHomepage</code> attribute.
     *
     * @return the value of <code>zHomepage</code>
     */     
    public byte[] getZHomepage()
    {
        return zHomepage;
    }
    
    /**
     * Sets the value of the <code>zHomepage</code> attribute.
     *
     * @param zHomepage the new value
     */     
    public void setZHomepage(byte[] zHomepage)
    {
        this.zHomepage = zHomepage;
    }
    
    /**
     * Returns the <code>zHomepage</code> attribute as a String.
     *
     * @return the String converted value of <code>zHomepage</code>
     * @see #getZHomepage()
     */         
    public String getZHomepageAsString()
    {
        byte[] url = getZHomepage();
        String res = "";
        for(int i = 0; i < url.length; i++)
        {
            if (url[i] == 0) break;
            res += (char)url[i];
        }        
        return res;        
    }

    /**
     * Returns the current value of <code>byAlphaLevel</code> attribute.
     *
     * @return the value of <code>byAlphaLevel</code>
     */     
    public byte getByAlphaLevel()
    {
        return byAlphaLevel;
    }

    /**
     * Sets the value of the <code>byAlphaLevel</code> attribute.
     *
     * @param byAlphaLevel the new value
     */    
    public void setByAlphaLevel(byte byAlphaLevel)
    {
        this.byAlphaLevel = byAlphaLevel;
    }

    /**
     * Returns the current value of <code>byBetaLevel</code> attribute.
     *
     * @return the value of <code>byBetaLevel</code>
     */    
    public byte getByBetaLevel()
    {
        return byBetaLevel;
    }

    /**
     * Sets the value of the <code>byBetaLevel</code> attribute.
     *
     * @param byBetaLevel the new value
     */    
    public void setByBetaLevel(byte byBetaLevel)
    {
        this.byBetaLevel = byBetaLevel;
    }

    /**
     * Returns the current value of <code>byMMXEnabled</code> attribute.
     *
     * @return the value of <code>byMMXEnabled</code>
     */    
    public byte getByMMXEnabled()
    {
        return byMMXEnabled;
    }

    /**
     * Sets the value of the <code>byMMXEnabled</code> attribute.
     *
     * @param byMMXEnabled the new value
     */    
    public void setByMMXEnabled(byte byMMXEnabled)
    {
        this.byMMXEnabled = byMMXEnabled;
    }

    /**
     * Returns the current value of <code>btReserved</code> attribute.
     *
     * @return the value of <code>btReserved</code>
     */    
    public byte[] getBtReserved()
    {
        return btReserved;
    }

    /**
     * Sets the value of the <code>btReserved</code> attribute.
     *
     * @param btReserved the new value
     */    
    public void setBtReserved(byte[] btReserved)
    {
        this.btReserved = btReserved;
    }
    
}
