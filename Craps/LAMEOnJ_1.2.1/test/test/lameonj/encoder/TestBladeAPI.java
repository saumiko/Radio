/*
 * LAMEOnJ Java based API for LAME MP3 encoder
 *
 * Copyright (c) 2006 Jose Maria Arranz
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

package test.lameonj.encoder;

import com.innowhere.jnieasy.core.JNIEasy;
import com.innowhere.jnieasy.core.data.NativeInteger;
import com.innowhere.jnieasy.core.typedec.NativeTypeManager;
import com.innowhere.jnieasy.core.util.NativeCapableFactory;
import lame.blade.BE_CONFIG;
import lame.blade.BE_VERSION;
import lame.blade.BladeMP3EncDLL;
import lameonj.encoder.blade.BeConfigLHV1;
import lameonj.encoder.blade.BeVersion;
import lameonj.LAMEOnJ;
import lameonj.encoder.blade.LAMEOnJBlade;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import lameonj.LAMEOnJBoot;

public class TestBladeAPI
{
    
    /** Creates a new instance of TestBladeAPI */
    public TestBladeAPI()
    {
    }
    
    public static void testPlainAPI(String wavFile,String mp3File) throws Exception
    {            
        // Based in the LAME example : Dll/Example.cpp
        
        new File(mp3File).delete();        
        
        NativeTypeManager typeMgr = JNIEasy.get().getTypeManager();
        
        BE_VERSION version = new BE_VERSION();
        BladeMP3EncDLL.beVersion(version);
        System.out.println("LAME Version: " + version.getByMajorVersion() + "." + version.getByMinorVersion());
        System.out.println("Home page: " + version.getZHomepageAsString());
        
        BE_CONFIG beConfig = new BE_CONFIG();     
        JNIEasy.get().getNativeManager().makeNative(beConfig); // Necessary NOW is a union
        
        beConfig.setDwConfig(BladeMP3EncDLL.BE_CONFIG_LAME);
        beConfig.getFormat().getLHV1().setDwStructVersion(1);
        beConfig.getFormat().getLHV1().setDwStructSize((int)typeMgr.sizeOf(BE_CONFIG.class));
        beConfig.getFormat().getLHV1().setDwSampleRate(44100);
        beConfig.getFormat().getLHV1().setDwReSampleRate(0); // NO RESAMPLE       
        beConfig.getFormat().getLHV1().setNMode(BladeMP3EncDLL.BE_MP3_MODE_JSTEREO);
        beConfig.getFormat().getLHV1().setDwBitrate(128);
        //beConfig.getFormat().getLHV1().setNPreset(LAME_QUALITY_PRESET.LQP_R3MIX);      
        beConfig.getFormat().getLHV1().setDwMpegVersion(BladeMP3EncDLL.MPEG1);
        beConfig.getFormat().getLHV1().setDwPsyModel(0);  //  USE DEFAULT PSYCHOACOUSTIC MODEL     
        beConfig.getFormat().getLHV1().setDwEmphasis(0); // NO EMPHASIS TURNED ON
        beConfig.getFormat().getLHV1().setBOriginal(true);
        beConfig.getFormat().getLHV1().setBWriteVBRHeader(true);  // Write INFO tag      
        beConfig.getFormat().getLHV1().setBNoRes(true);

        
        NativeCapableFactory factory = JNIEasy.get().getNativeCapableFactory();
        NativeInteger pDwSamples = factory.newNativeInteger(0);
        NativeInteger pDwMP3Buffer = factory.newNativeInteger(0);        
        NativeInteger pHbeStream = factory.newNativeInteger(0); 
        
        int err = BladeMP3EncDLL.beInitStream(beConfig,pDwSamples,pDwMP3Buffer,pHbeStream);                
        checkError(err);

        int dwSamples = pDwSamples.getIntValue();
        int dwMP3Buffer = pDwMP3Buffer.getIntValue();
        int hbeStream = pHbeStream.getIntValue();
        
        // Allocate buffers
        //short[] pWAVBuffer = new short[dwSamples];  
        byte[] pWAVBuffer = new byte[dwSamples * 2];  // 2 bytes per short     
        byte[] pMP3Buffer = new byte[dwMP3Buffer];     
        
        
        // WAV file supposed 44100 Hz, Stereo, 16 bits
        BufferedInputStream wavStream = new BufferedInputStream(new FileInputStream(wavFile));       
        BufferedOutputStream mp3Stream = new BufferedOutputStream(new FileOutputStream(mp3File));
        
        wavStream.skip(44); // Skipping the WAV header        
        
        NativeInteger pDwWrite = factory.newNativeInteger(0);
        int dwWrite;
        // Convert All PCM samples
        int read = 0;        
        while ((read = wavStream.read(pWAVBuffer,0,pWAVBuffer.length)) > 0)
        {
            // read is "bytes", each sample is 2 bytes
            int readSamples = read / 2; 
            // Encode samples
            err = BladeMP3EncDLL.beEncodeChunk(hbeStream, readSamples , pWAVBuffer, pMP3Buffer, pDwWrite);            
            checkError(err);  
            
            // write dwWrite bytes that are returned in tehe pMP3Buffer to disk
            dwWrite = pDwWrite.getIntValue();
            mp3Stream.write(pMP3Buffer,0,dwWrite);
        }
        
	// Deinit the stream
	err = BladeMP3EncDLL.beDeinitStream(hbeStream, pMP3Buffer, pDwWrite);        
        checkError(err);
        
	// Are there any bytes returned from the DeInit call?
	// If so, write them to disk        
        dwWrite = pDwWrite.getIntValue();        
	if( dwWrite != 0 )
            mp3Stream.write(pMP3Buffer,0,dwWrite);

	// close the MP3 Stream
	BladeMP3EncDLL.beCloseStream( hbeStream );        
        
        wavStream.close();
        mp3Stream.close();                
                
        if (beConfig.getFormat().getLHV1().isBWriteVBRHeader())
            BladeMP3EncDLL.beWriteVBRHeader(mp3File);   
        
        // Test.checkFiles(mp3File,mp3RefFile,false);        
    }
    
    public static void testOOPAPI(String wavFile,String mp3File) throws Exception
    {            
        // Based in the LAME example : Dll/Example.cpp           
        
        new File(mp3File).delete();
        
        NativeTypeManager typeMgr = JNIEasy.get().getTypeManager();
        
        LAMEOnJ lame = LAMEOnJBoot.get();
        LAMEOnJBlade blade = lame.getLAMEOnJBlade();
        
        
        BeVersion version = blade.getVersion();
        System.out.println("LAME Version: " + version.getByMajorVersion() + "." + version.getByMinorVersion());
        System.out.println("Home page: " + version.getZHomepageAsString());
        
        BeConfigLHV1 beConfig = blade.newBeConfigLHV1();     
        
        beConfig.setDwSampleRate(44100);
        beConfig.setDwReSampleRate(0); // NO RESAMPLE       
        beConfig.setNMode(BladeMP3EncDLL.BE_MP3_MODE_JSTEREO);
        beConfig.setDwBitrate(128);
        //beConfig.setNPreset(LAME_QUALITY_PRESET.LQP_R3MIX);      
        beConfig.setDwMpegVersion(BladeMP3EncDLL.MPEG1);
        beConfig.setDwPsyModel(0);  //  USE DEFAULT PSYCHOACOUSTIC MODEL     
        beConfig.setDwEmphasis(0); // NO EMPHASIS TURNED ON
        beConfig.setBOriginal(true);
        beConfig.setBWriteVBRHeader(true);  // Write INFO tag      
        beConfig.setBNoRes(true);

        blade.encode(wavFile,mp3File,beConfig);  
        
        // Test.checkFiles(mp3File,mp3RefFile,false);        
    }       
   
    public static void checkError(int err)
    {
        if (err != BladeMP3EncDLL.BE_ERR_SUCCESSFUL)
            throw new RuntimeException("ERROR " + err);     
    }        
}
