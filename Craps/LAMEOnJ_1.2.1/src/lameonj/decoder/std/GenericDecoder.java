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

import lame.std.mp3data_struct;


/**
 * This interface may be used to progressively decode MP3 data to PCM.
 *
 * <p>A decoding process must start decoding MP3 headers, the method
 * {@link #decodeHeaders(byte[],int)} must be called until it returns a non-null
 * MP3 header object.
 * </p>
 *
 * @see LAMEOnJStdDecoder#createGenericDecoder()
 */
public interface GenericDecoder extends Decoder
{
   
    /**
     * Process the specified MP3 data containing MP3 header info.
     * 
     * <p>If this method returns null, specified MP3 data is not enough 
     * to extract header info. Call again until returns a non-null object.
     * </p>
     * 
     * <p>Submitted MP3 data can contain several MP3 frames, not processed
     * data is queued internally and used later. Call {@link #decodePendingFrame()}
     * to process pending MP3 frames to avoid excessive memory consumption.
     * </p>
     * 
     * @param mp3buf buffer containing MP3 data to decode.
     * @param size the number of bytes of the MP3 buffer to process.
     * @return the MP3 header object. Null if not enough MP3 data was submitted.
     */
    public mp3data_struct decodeHeaders(byte[] mp3buf,int size);
    
    /**
     * Decodes the specified MP3 data to build one PCM frame. 
     * 
     * <p>Any internal MP3 data pending to process is used first to build
     * the PCM frame.
     * </p> 
     * 
     * <p>If submitted MP3 data is not enough returns null. Call again
     * providing new data.
     * </p>
     * 
     * <p>Submitted MP3 data can contain several MP3 frames, not processed
     * data is queued internally and used later. Before calling
     * this method, call {@link #decodePendingFrame()}
     * to process pending MP3 frames to avoid excessive memory consumption.
     * </p>
     * 
     * @param mp3buf buffer containing MP3 data to decode.
     * @param size the number of bytes of the MP3 buffer to process.      
     * @return a PCM frame object. Null if not enough MP3 data was submitted.
     */
    public PCMFrame decodeFrame(byte[] mp3buf,int size);    
    
    /**
     * Decodes any pending (not processed) MP3 data to build one PCM frame. 
     * 
     * <p>If pending MP3 data is not enough to build a PCM frame returns null.</p>
     * 
     * <p>Before submitting new MP3 data to the decoder call this method several 
     * times until returns null to avoid excessive memory consumption.
     * </p>
     * 
     * @return a PCM frame object. Null if not enough MP3 data is pending.
     */
    public PCMFrame decodePendingFrame();    
}
