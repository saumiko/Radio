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

import com.innowhere.jnieasy.core.JNIEasy;
import com.innowhere.jnieasy.core.data.NativeInteger;
import lame.blade.BladeMP3EncDLL;
import lameonj.LAMEOnJException;

public class LAMEBladeUtil
{
    
    /**
     * Creates a new instance of LAMEBladeUtil
     */
    public LAMEBladeUtil()
    {
    }
    
    public static NativeInteger newNativeInteger()
    {
        return JNIEasy.get().getNativeCapableFactory().newNativeInteger(0);
    }    

    public static void checkError(int err)
    {
        if (err != BladeMP3EncDLL.BE_ERR_SUCCESSFUL)        
            throw new LAMEOnJException(LAMEBladeUtil.getErrorMessage(err),err);         
    }    
    
    public static String getErrorMessage(int err)        
    {
        String msg = "";
        switch(err)
        {
            case BladeMP3EncDLL.BE_ERR_INVALID_FORMAT: 
                    msg += "BE_ERR_INVALID_FORMAT"; break;
            case BladeMP3EncDLL.BE_ERR_INVALID_FORMAT_PARAMETERS: 
                    msg += "BE_ERR_INVALID_FORMAT_PARAMETERS"; break;
            case BladeMP3EncDLL.BE_ERR_NO_MORE_HANDLES: 
                    msg += "BE_ERR_NO_MORE_HANDLES"; break;
            case BladeMP3EncDLL.BE_ERR_INVALID_HANDLE: 
                    msg += "BE_ERR_INVALID_HANDLE"; break;                
            case BladeMP3EncDLL.BE_ERR_BUFFER_TOO_SMALL: 
                    msg += "BE_ERR_BUFFER_TOO_SMALL"; break; 
            default:
                    msg += err;
        }   
        return msg;
    }
}
