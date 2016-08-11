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

import lameonj.decoder.std.LAMEOnJStdDecoder;
import lameonj.encoder.blade.LAMEOnJBlade;
import lameonj.encoder.std.LAMEOnJStdEncoder;

/**
 * The <code>LAMEOnJ</code> is the starting object of the LAMEOnJ
 * utilities using an object oriented fashion to work with the LAME reflected 
 * C elements.
 * 
 * <p><code>LAMEOnJ</code> is an interface, to get the singleton instance implementing
 * this interface call the method: {@link LAMEOnJBoot#get()}. </p>
 *
 */
public interface LAMEOnJ
{
   
    /**
     * Returns the singleton object to access LAME using Blade MP3 encoder API (only defined on Windows)
     *
     * @return the Blade MP3 API singleton
     */
    public LAMEOnJBlade getLAMEOnJBlade();
    
    /**
     * Returns the singleton object to access LAME encoder using standard API
     *
     * @return the LAME standard encoder singleton     
     */
    public LAMEOnJStdEncoder getLAMEOnJStdEncoder();
    
    /**
     * Returns the singleton object to access LAME decoder using standard API
     *
     * @return the LAME standard decoder singleton     
     */
    public LAMEOnJStdDecoder getLAMEOnJStdDecoder();    
}
