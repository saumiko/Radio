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

package lameonj.decoder.std;

import java.io.InputStream;

/**
 * The <code>LAMEOnJStdDecoder</code> is the starting interface of the LAMEOnJ
 * decoder using an object oriented fashion to work with the LAME reflected 
 * C methods using the LAME standard API.
 * 
 * <p>LAME decoder API only supports one decoding task open. Do not forget
 * calling {@link Decoder#close()} before trying to create a new decoding task.
 * </p>
 * 
 * @see lameonj.LAMEOnJ#getLAMEOnJStdDecoder()
 */
public interface LAMEOnJStdDecoder
{
    /**
     * Creates a new MP3 stream decoder using the specified MP3 file as audio source.
     *
     * @param file the path of the MP3 audio source.
     * @return a new MP3 stream decoder.
     */    
    public StreamDecoder createStreamDecoder(String file);
   
    /**
     * Creates a new MP3 stream decoder using the specified MP3 stream as audio source.
     *
     * <p>Initial data of the stream must contain the MP3 header.</p>
     *
     * @param stream the stream of the MP3 audio source.
     * @return a new MP3 stream decoder
     */ 
    public StreamDecoder createStreamDecoder(InputStream stream);  
    
    /**
     * Creates a new generic MP3 stream decoder.
     *
     * @return a new MP3 stream decoder
     */   
    public GenericDecoder createGenericDecoder();
    
    /**
     * Informs whether there is an unfinished decoding task.
     * 
     * @return true if exists a decoding task open.
     */
    public boolean isDecoderBlocked();
}
