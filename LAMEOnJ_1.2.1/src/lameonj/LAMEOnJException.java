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

package lameonj;

/**
 * The <code>LAMEOnJException</code> is the exception class used to throw errors
 * detected inside the LAMEOnJ library.
 * <br />
 * If a LAME method returns an non-null error code, this code is used to raise
 * this exception.
 */
public class LAMEOnJException extends RuntimeException
{
    protected int code;
    
    /**
     * Constructs a new exception with the specified detail message.
     * <br/>
     * The corresponding base constructor is called.
     *
     * @param message the detail message.
     */
    public LAMEOnJException(String message)
    {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     * <br/>
     * The corresponding base constructor is called.
     *
     * @param message the detail message.
     * @param cause the cause.
     */    
    public LAMEOnJException(String message, Throwable cause) 
    {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause.
     * <br/>
     * The corresponding base constructor is called.
     *
     * @param cause the cause.
     */     
    public LAMEOnJException(Throwable cause) 
    {
        super(cause);
    }
    
    /**
     * Constructs a new exception with the specified LAME error code.
     *
     * @param msg explanation message.
     * @code the LAME error code.
     * @see #getCode()
     */     
    public LAMEOnJException(String msg,int code)
    {
        super(msg);
        this.code = code;
    }    

    
    /**
     * Returns the LAME error code.
     *
     * @return the LAME error code if was defined else returns 0.
     * @see #LAMEOnJException(String,int)
     */     
    public int getCode()
    {
        return code;
    }
}
