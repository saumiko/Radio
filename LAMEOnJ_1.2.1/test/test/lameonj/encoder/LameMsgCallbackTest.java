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

package test.lameonj.encoder;

import lame.std.LameMsgCallback;

/**
 *
 * @author jmarranz
 */
public class LameMsgCallbackTest extends LameMsgCallback
{
    
    /** Creates a new instance of LameMsgCallbackTest */
    public LameMsgCallbackTest()
    {
    }
        
    public void msg(String format, int params)
    {
        System.out.println("MSG: " + format); 
    }    
}
