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

import java.io.InputStream;
import java.io.OutputStream;

/**
 * The <code>LAMEOnJBlade</code> is the starting interface of the LAMEOnJ
 * utilities using an object oriented fashion to work with the LAME reflected 
 * C elements using the Blade MP3 Encoder API.
 *
 */
public interface LAMEOnJBlade
{
    /**
     * Returns data about the LAME version used.
     * <br />
     * To construct the <code>BeVersion</code> object, the method
     * {@link lame.blade.BladeMP3EncDLL#beVersion(lame.blade.BE_VERSION)} is called.
     *
     * @return the version object containing the LAME info.
     * @see BeVersion
     */    
    public BeVersion getVersion();
    
    /**
     * Creates a new generic configuration object.
     *
     * @return the new configuration object.
     * @see BeConfig
     */     
    public BeConfig newBeConfig();    
    
    /**
     * Creates a new configuration object using the 
     * <code>mp3</code> attribute.
     *
     * @return the new configuration object.
     * @see BeConfigMP3
     */    
    public BeConfigMP3 newBeConfigMP3();
    
    /**
     * Creates a new configuration object using the 
     * <code>LHV1</code> attribute.
     *
     * @return the new configuration object.
     * @see BeConfigLHV1
     */    
    public BeConfigLHV1 newBeConfigLHV1(); 
   
    /**
     * Creates a new configuration object using the 
     * <code>aac</code> attribute.
     *
     * @return the new configuration object.
     * @see BeConfigAAC
     */    
    public BeConfigAAC newBeConfigAAC();
    
    /**
     * Creates (initializes) a new LAME encoding stream.
     * <br />
     * The returned {@link BeStream} is created and initialized
     * with the data returned by the call
     * {@link lame.blade.BladeMP3EncDLL#beInitStream(BE_CONFIG,NativeInteger,NativeInteger,NativeInteger)}.
     *
     * @param beConfig the encoding configuration object used to create the stream.
     * @return a new opened encoding stream.
     */
    public BeStream initStream(BeConfig beConfig);
    
    /**
     * Encodes the input WAV file and writes the MP3 output to the specified file.
     * <br />
     * The file must be a WAV file.
     * <p>If VBR tag info is specified is automatically added.</p>
     *
     * @param wavFile the input path of WAV file to be encoded.
     * @param mp3File the output path of the MP3 file to be encoded.
     * @param beConfig the encoding configuration info used.
     * @see #encode(InputStream,OutputStream,BeConfig,boolean)
     */
    public void encode(String wavFile,String mp3File,BeConfig beConfig);
    
    
    /**
     * Encodes the input stream and writes the MP3 output to the specified output stream.
     * <br />
     * <p>If the <code>isWav</code> parameter is set to true the stream (is supposed)
     * starts with a WAV header, else is considered as a LPCM stream.
     * <br />
     * <p>Streams are NOT closed and VBR info can not be added.</p>
     *
     * @param input the input stream to be encoded.
     * @param mp3Output the output stream to write the MP3 encoded output.
     * @param beConfig the encoding configuration info used.
     * @param isWav if the input stream has a WAV header.
     */    
    public void encode(InputStream input,OutputStream mp3Output,BeConfig beConfig,boolean isWav);        
}
