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
import lame.blade.BE_CONFIG;
import lame.blade.BE_VERSION;
import lame.blade.BladeMP3EncDLL;
import lameonj.LAMEOnJException;
import lameonj.encoder.blade.BeConfig;
import lameonj.encoder.blade.BeConfigAAC;
import lameonj.encoder.blade.BeConfigLHV1;
import lameonj.encoder.blade.BeConfigMP3;
import lameonj.encoder.blade.BeStream;
import lameonj.encoder.blade.BeVersion;
import lameonj.encoder.blade.LAMEOnJBlade;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author jmarranz
 */
public class LAMEOnJBladeImpl implements LAMEOnJBlade
{
    
    /** Creates a new instance of LAMEOnJBladeImpl */
    public LAMEOnJBladeImpl()
    {
    }
    
    public BeConfig newBeConfig()
    {
        return new BeConfigImpl();
    }        
    
    public BeConfigMP3 newBeConfigMP3()
    {
        return new BeConfigMP3Impl();
    }    
    
    public BeConfigLHV1 newBeConfigLHV1()
    {
        return new BeConfigLAMEImpl();
    }        
    
    public BeConfigAAC newBeConfigAAC()
    {
        return new BeConfigAACImpl();
    }
    
    public BeVersion getVersion()
    {
        BE_VERSION version = new BE_VERSION();
        BladeMP3EncDLL.beVersion(version);
        return new BeVersionImpl(version);
    }
    
    public BeStream initStream(BeConfig beConfig)
    {
        NativeInteger pDwSamples = LAMEBladeUtil.newNativeInteger();
        NativeInteger pDwMP3Buffer = LAMEBladeUtil.newNativeInteger();
        NativeInteger pHbeStream = LAMEBladeUtil.newNativeInteger();
        
        int err = BladeMP3EncDLL.beInitStream(beConfig.getBE_CONFIG(),pDwSamples,pDwMP3Buffer,pHbeStream);                
        LAMEBladeUtil.checkError(err); 
        
        int nSamples = pDwSamples.getIntValue();
        int outputBufferSize = pDwMP3Buffer.getIntValue();
        int nhbeStream = pHbeStream.getIntValue();
        return new BeStreamImpl(nhbeStream,nSamples,outputBufferSize);
    }    
    
    public void encode(String wavFile,String mp3File,BeConfig beConfig)
    {
          
        FileInputStream wavStream = null;
        FileOutputStream mp3Stream = null;
        try
        {
            wavStream = new FileInputStream(wavFile);       
            mp3Stream = new FileOutputStream(mp3File);
        
            encode(wavStream,mp3Stream,beConfig,true);          
        }
        catch(IOException ex)
        {
            throw new LAMEOnJException(ex);
        }        
        finally
        {           
            try
            {
                if (wavStream != null) wavStream.close();
                if (mp3Stream != null) mp3Stream.close();            
            }
            catch(IOException ex)
            {
                throw new LAMEOnJException(ex);
            }            
        }
        
        BE_CONFIG conf = beConfig.getBE_CONFIG();
        if (conf.getFormat().getLHV1().isBWriteVBRHeader())
        {
            BladeMP3EncDLL.beWriteVBRHeader(mp3File);        
        }
    }
   
    public void encode(InputStream inputStream,OutputStream mp3Stream,BeConfig beConfig,boolean isWav)
    {
        if (!(inputStream instanceof BufferedInputStream))
            inputStream = new BufferedInputStream(inputStream);
        
        if (!(mp3Stream instanceof BufferedOutputStream))
            mp3Stream = new BufferedOutputStream(mp3Stream);        
        
        BeStream beStream = initStream(beConfig);       

        int dwSamples = beStream.getNSamples();
        int dwMP3Buffer = beStream.getOutputBufferSize();
        //int hbeStream = beStream.getHandle();
        
        // Allocate buffers   
        //short[] pWAVBuffer = new short[dwSamples];  
        byte[] pWAVBuffer = new byte[dwSamples * 2]; // each sample is a short      
        byte[] pMP3Buffer = new byte[dwMP3Buffer];          
        
        try
        {
            if (isWav) inputStream.skip(44); // Skipping the WAV header        

            int dwWrite;
            // Convert All PCM samples
            int read = 0;        
            while ((read = inputStream.read(pWAVBuffer,0,pWAVBuffer.length)) > 0)
            {
                // read is "bytes", each sample is 2 bytes
                int readSamples = read / 2;                 
                // Encode samples
                dwWrite = beStream.encodeChunk(readSamples,pWAVBuffer, pMP3Buffer); 

                // write dwWrite bytes that are returned in the pMP3Buffer to disk
                mp3Stream.write(pMP3Buffer,0,dwWrite);
            }

            // Deinit the beStream
            dwWrite = beStream.deinitStream(pMP3Buffer);

            // Are there any bytes returned from the DeInit call?
            // If so, write them to disk              
            if( dwWrite != 0 )
                mp3Stream.write(pMP3Buffer,0,dwWrite);            
                
            mp3Stream.flush();  // IMPORTANT because BufferedInputStream is lost and not closed
        }
        catch(IOException ex)
        {
            throw new LAMEOnJException(ex);
        }
      
        beStream.close();            
        
    }        
}
