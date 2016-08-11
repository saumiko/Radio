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
    typedef struct {
        // generic LAME version 
        int major;
        int minor;
        int alpha;               // 0 if not an alpha version                  
        int beta;                // 0 if not a beta version                    

        // version of the psy model 
        int psy_major;
        int psy_minor;
        int psy_alpha;           // 0 if not an alpha version                  
        int psy_beta;            // 0 if not a beta version                    

        // compile time features 
        const char *features;    // Don't make assumptions about the contents! 
    } lame_version_t;

 */
public class lame_version_t
{
    protected int major;
    protected int minor;
    protected int alpha;               // 0 if not an alpha version                  
    protected int beta;                // 0 if not a beta version                    

    // version of the psy model 
    protected int psy_major;
    protected int psy_minor;
    protected int psy_alpha;           // 0 if not an alpha version                  
    protected int psy_beta;            // 0 if not a beta version                    

    // compile time features 
    protected String features;    // Don't make assumptions about the contents!    
    
    
    /** Creates a new instance of lame_version_t */
    public lame_version_t()
    {
    }

    public int getMajor()
    {
        return major;
    }

    public void setMajor(int major)
    {
        this.major = major;
    }

    public int getMinor()
    {
        return minor;
    }

    public void setMinor(int minor)
    {
        this.minor = minor;
    }

    public int getAlpha()
    {
        return alpha;
    }

    public void setAlpha(int alpha)
    {
        this.alpha = alpha;
    }

    public int getBeta()
    {
        return beta;
    }

    public void setBeta(int beta)
    {
        this.beta = beta;
    }

    public int getPsy_major()
    {
        return psy_major;
    }

    public void setPsy_major(int psy_major)
    {
        this.psy_major = psy_major;
    }

    public int getPsy_minor()
    {
        return psy_minor;
    }

    public void setPsy_minor(int psy_minor)
    {
        this.psy_minor = psy_minor;
    }

    public int getPsy_alpha()
    {
        return psy_alpha;
    }

    public void setPsy_alpha(int psy_alpha)
    {
        this.psy_alpha = psy_alpha;
    }

    public int getPsy_beta()
    {
        return psy_beta;
    }

    public void setPsy_beta(int psy_beta)
    {
        this.psy_beta = psy_beta;
    }

    public String getFeatures()
    {
        return features;
    }

    public void setFeatures(String features)
    {
        this.features = features;
    }
    
}
