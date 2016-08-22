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
 * @author jmarranz
 */
public class FileUtil
{
    
    /** Creates a new instance of FileUtil */
    public FileUtil()
    {
    }
        
    /**
     * <pre>FILE *fopen(const char *filename, const char *mode); </pre>
     */
    public static FILE fopen(String filename,String mode)
    {
        throw new RuntimeException("Must be enhanced");
    } 
    
    /**
     * <pre>int fclose(FILE *stream);</pre>
     */
    public static int fclose(FILE stream)
    {
        throw new RuntimeException("Must be enhanced");
    }             

    public static class FILE
    {

    }    
}

