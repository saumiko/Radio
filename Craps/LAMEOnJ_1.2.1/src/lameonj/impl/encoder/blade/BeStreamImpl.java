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

import com.innowhere.jnieasy.core.data.NativeInteger;
import lame.blade.BladeMP3EncDLL;
import lameonj.encoder.blade.BeStream;
import lameonj.LAMEOnJException;

public class BeStreamImpl implements BeStream
{
    protected int hbeStream;
    protected int nSamples;
    protected int outputBufferSize;  
    
    /**
     * Creates a new instance of BeStreamImpl
     */
    public BeStreamImpl(int hbeStream,int nSamples,int outputBufferSize)
    {
        this.hbeStream = hbeStream;
        this.nSamples = nSamples;
        this.outputBufferSize = outputBufferSize;       
    }
   
    protected void finalize()
    {
        if (hbeStream != 0)
            close();
    }
    
    private void checkStreamOpen()
    {
        if (hbeStream == 0) throw new LAMEOnJException("Stream is closed");
    }
    
    public int getHandle()
    {
        return hbeStream;
    }
    
    public int getNSamples()
    {
        return nSamples;
    }
    
    public int getOutputBufferSize()
    {
        return outputBufferSize;
    }
    
    public boolean isOpen()
    {
        return hbeStream != 0;
    }
    
    public void close()
    {
        checkStreamOpen();        
        int err = BladeMP3EncDLL.beCloseStream(hbeStream);
        LAMEBladeUtil.checkError(err);
        this.hbeStream = 0;
    }    
 
    public int deinitStream(byte[] pOutput)
    {
        checkStreamOpen();         
        NativeInteger pdwOutput = LAMEBladeUtil.newNativeInteger();
        int err = BladeMP3EncDLL.beDeinitStream(hbeStream,pOutput,pdwOutput);
        LAMEBladeUtil.checkError(err);
        return pdwOutput.getIntValue();
    }  
    
    public int encodeChunk(int nSamples,short[] pSamples,byte[] pOutput)
    {
        checkStreamOpen();         
        NativeInteger pdwOutput = LAMEBladeUtil.newNativeInteger();
        int err = BladeMP3EncDLL.beEncodeChunk(hbeStream,nSamples,pSamples,pOutput,pdwOutput);
        LAMEBladeUtil.checkError(err);
        return pdwOutput.getIntValue();
    }
    
    public int encodeChunk(int nSamples,byte[] pSamples,byte[] pOutput)
    {
        checkStreamOpen();         
        NativeInteger pdwOutput = LAMEBladeUtil.newNativeInteger();
        int err = BladeMP3EncDLL.beEncodeChunk(hbeStream,nSamples,pSamples,pOutput,pdwOutput);
        LAMEBladeUtil.checkError(err);
        return pdwOutput.getIntValue();
    }   
    
    public int encodeChunkFloatS16NI(int hbeStream,int nSamples,float[] buffer_l,float[] buffer_r,byte[] pOutput)
    {
        checkStreamOpen();         
        NativeInteger pdwOutput = LAMEBladeUtil.newNativeInteger();
        int err = BladeMP3EncDLL.beEncodeChunkFloatS16NI(hbeStream,nSamples,buffer_l,buffer_r,pOutput,pdwOutput);
        LAMEBladeUtil.checkError(err);
        return pdwOutput.getIntValue();
    }    
    
    public int flushNoGap(int hbeStream,byte[] pOutput)
    {
        checkStreamOpen();         
        NativeInteger pdwOutput = LAMEBladeUtil.newNativeInteger();
        int err = BladeMP3EncDLL.beFlushNoGap(hbeStream,pOutput,pdwOutput);
        LAMEBladeUtil.checkError(err);
        return pdwOutput.getIntValue();
    }         
}
