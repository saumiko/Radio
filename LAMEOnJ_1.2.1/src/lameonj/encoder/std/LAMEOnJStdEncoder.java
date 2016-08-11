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

package lameonj.encoder.std;

import java.io.InputStream;

/**
 * The <code>LAMEOnJStdEncoder</code> is the starting interface of the LAMEOnJ
 * encoder using an object oriented fashion to work with the LAME reflected 
 * C methods using the LAME standard API.
 *
 * @see lameonj.LAMEOnJ#getLAMEOnJStdEncoder()
 */
public interface LAMEOnJStdEncoder
{
    /**
     * Creates a new MP3 stream encoder using the specified file as audio source.
     *
     * <p>If the specified file is a WAV file, the header is processed and 
     * extracted data as number of channels, mono/stereo, and sample rate are
     * used to set up the LAME encoder.
     * </p>
     *
     * @param file the path of the audio source.
     * @return a new MP3 stream encoder
     */    
    public StreamEncoder createStreamEncoder(String file);

    /**
     * Creates a new MP3 stream encoder using the specified stream as audio source.
     *
     * <p>MIME type may be specified, if specified current version only supports WAV
     * (audio/wav, audio/wave, audio/x-wav), otherwise raw PCM is supposed.</p>
     *
     * <p>If WAV the header is processed as explained in {@link #createStreamEncoder(String)}</p>
     *
     * @param stream the stream of the audio source.
     * @param mime the MIME type of the source. May be null (raw PCM).
     * @return a new MP3 stream encoder
     */ 
    public StreamEncoder createStreamEncoder(InputStream stream,String mime);       
    
    /**
     * Creates a new generic MP3 stream encoder.
     *
     * <p>If the specified file is a WAV file, the header is processed and 
     * extracted data as number of channels, mono/stereo, and sample rate are
     * used to set up the LAME encoder.
     * </p>
     *
     * @return a new MP3 stream encoder
     */   
    public GenericEncoder createGenericEncoder();
}
