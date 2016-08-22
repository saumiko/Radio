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

import com.innowhere.jnieasy.core.data.NativeInteger;
import lame.blade.BladeMP3EncDLL;

/**
 * The <code>BeStream</code> is the interface implemented by the internal
 * class wrapping the encoding stream lifecycle.
 * <br />
 * Unless the {@link #close()} is called a <code>BeStream</code>
 * holds a previously initiated LAME stream with: 
 * {@link BladeMP3EncDLL#beInitStream(BE_CONFIG,NativeInteger,NativeInteger,NativeInteger)}.
 * <br />
 * If the stream is closed the instance must be discarded.
 *
 * @see LAMEOnJBlade#initStream(BeConfig)
 */
public interface BeStream
{
    /**
     * Returns the stream handle used by LAME. 
     *
     * This is the handle returned in the <code>phbeStream</code> parameter by the call 
     * {@link BladeMP3EncDLL#beInitStream(BE_CONFIG,NativeInteger,NativeInteger,NativeInteger)}
     * and used to encode.
     * <br />
     *
     * @return the stream handle. If 0 the stream is closed.
     */      
    public int getHandle();
    
    /**
     * Returns the maximum number of samples per encoding call. 
     *
     * This is the value returned in the <code>pDwSamples</code> parameter by the call 
     * {@link BladeMP3EncDLL#beInitStream(BE_CONFIG,NativeInteger,NativeInteger,NativeInteger)}.
     *
     * @return the stream handle.
     * @see #encodeChunk(int,short[],byte[])
     */  
    public int getNSamples();
    
    /**
     * Returns the maximum output buffer size per encoding call. 
     *
     * This is the value returned in the <code>pDwSamples</code> parameter by the call 
     * {@link BladeMP3EncDLL#beInitStream(BE_CONFIG,NativeInteger,NativeInteger,NativeInteger)}.
     *
     * @return the maximum output buffer size.
     * @see #encodeChunk(int,short[],byte[])
     */
    public int getOutputBufferSize();
    
    /**
     * Returns true if the encoding stream is open.
     * 
     * @return true if open.
     */
    public boolean isOpen();
    
    /**
     * Closes the stream, the instance must be discarded.
     * <br />
     * The method {@link BladeMP3EncDLL#beCloseStream(int)} is called.
     */    
    public void close();
 
    /**
     * Flushes to the output buffer the internal encoder data remaining.
     * <br />
     * The method {@link BladeMP3EncDLL#beInitStream(BE_CONFIG,NativeInteger,NativeInteger,NativeInteger)} 
     * is called.
     *
     * @param pOutput the output buffer receiving the remaining data.
     * @return the number of bytes written to the output buffer.
     */     
    public int deinitStream(byte[] pOutput);
    
    /**
     * Encodes a chunk of audio samples, the output data is written to the output buffer.
     * <br />
     * The method {@link BladeMP3EncDLL#beEncodeChunk(int,int,short[],byte[],NativeInteger)} 
     * is called.
     *
     * @param nSamples the number of samples to be encoded.
     * @param pSamples the samples buffer to be written.
     * @param pOutput the output buffer receiving the encoded data.
     * @return the number of bytes written to the output buffer.
     */    
    public int encodeChunk(int nSamples,short[] pSamples,byte[] pOutput);
    
    /**
     * Encodes a chunk of audio samples, the output data is written to the output buffer.
     * <br />
     * This is a convenience method using a byte[] samples buffer instead a short[] buffer.
     * The byte[] buffer must be 2x the size of the corresponding short[] buffer.
     *
     * @param nSamples the number of samples to be encoded.
     * @param pSamples the samples buffer in bytes (two bytes per sample) to be written.
     * @param pOutput the output buffer receiving the encoded data.
     * @return the number of bytes written to the output buffer.
     * @see #encodeChunk(int,short[],byte[])
     */
    public int encodeChunk(int nSamples,byte[] pSamples,byte[] pOutput);
    
    /**
     * Undocumented method.
     */
    public int encodeChunkFloatS16NI(int hbeStream,int nSamples,float[] buffer_l,float[] buffer_r,byte[] pOutput);
    
    /**
     * Undocumented method.
     */
    public int flushNoGap(int hbeStream,byte[] pOutput);
}
