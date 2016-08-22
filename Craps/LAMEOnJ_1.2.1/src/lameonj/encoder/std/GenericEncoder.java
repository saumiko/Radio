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

/**
 * This interface may be used to progressively encode PCM data to MP3.
 *
 * <p>LAME flags must be set up before starting an encoding task, LAME
 * uses the default configuration and no configuration data (number of channels etc)
 * is extracted from the audio data provided to the encoder, in fact
 * only raw PCM is supposed. Call the {@link #getEncoderConfig()} method
 * to config the encoder.</p>
 *
 * <p>A complete encoding process does not close the encoder and can be reused.
 * The encoder must be closed explicitly (see {@link Encoder#close()}).</p>
 *
 * <p>The encoding process does not add the VBR Info tag, you can use the  
 * method {@link Encoder#writeVbrTag(String)}</p>
 *
 * <p>Note: if you want to encode a WAV file using this interface, 
 * skip the WAV header first (skips the first 44 bytes of the file)</p>
 *
 * @see LAMEOnJStdEncoder#createGenericEncoder()
 */
public interface GenericEncoder extends Encoder
{
    /**
     * Starts an encoding process.
     * 
     * <p>The required size of source PCM and target MP3 
     * byte buffers are returned using the first array item of the provided parameters.
     * </p>
     *
     * @param pcmBufferSize the one element array where the required PCM buffer size is saved.
     * @param mp3BufferSize the one element array where the required MP3 buffer size is saved.     
     */
    public void initEncoding(int[] pcmBufferSize, int[] mp3BufferSize);    
    
    /**
     * Encodes a chunk of raw PCM data, if stereo source audio data must be interleaved.
     * 
     * <p>PCM and MP3 buffers must have the lengths provided by the method
     * {@link #initEncoding(int[],int[])}</p>
     * 
     * 
     * @param pcmBuffer the PCM data to encode.
     * @param size the number of bytes with PCM data.
     * @param mp3Buffer the MP3 buffer to fill with the encoded MP3 audio.
     * @return the number of bytes used to fill the MP3 buffer.
     */    
    public int encodeBuffer(byte[] pcmBuffer,int size,byte[] mp3Buffer);
    
    /**
     * Finalizes the encoding task, any remainder MP3 data in the encoder is flushed
     * to the MP3 buffer.
     *
     * @param mp3Buffer the MP3 buffer to fill with the encoded MP3 audio.
     * @return the number of bytes used to fill the MP3 buffer.
     */      
    public int encodeFlush(byte[] mp3Buffer);    
}
