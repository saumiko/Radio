/*
 * LAMEOnJ Java based API for Lame MP3 encoder
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

import lame.std.FileUtil;
import lame.std.Lame;
import lame.std.MPEG_mode;
import lame.std.Padding_type;
import lame.std.lame_errorcodes_t;
import lame.std.lame_global_flags;
import lameonj.LAMEOnJ;
import lameonj.encoder.std.GenericEncoder;
import lameonj.encoder.std.LAMEOnJStdEncoder;
import lameonj.encoder.std.EncoderConfig;
import lameonj.encoder.std.StreamEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import lameonj.LAMEOnJBoot;

public class TestEncoderStdAPI
{
    
    /** Creates a new instance of TestStdAPI */
    public TestEncoderStdAPI()
    {
    }    
   
    public static void testPlainAPI(String wavFile,String mp3File) throws Exception
    {   
        // view-source:http://stuff.mit.edu/afs/sipb/user/gamache/src/lame-3.91/Dll/BladeMP3EncDLL.c        
        // http://www.koders.com/cpp/fid8B71B2E32247F195DA00237E702C5031468A55C1.aspx?s=lame_encode_buffer        
        
        new File(mp3File).delete(); 
        
        lame_global_flags flags = Lame.lame_init();                    
        
        System.out.println("LAME Version: " + Lame.get_lame_version());
        System.out.println("Home page: " + Lame.get_lame_url());                      
        
        int rc;
        rc = Lame.lame_set_error_protection(flags, 1);
        checkError(rc);
        
        rc = Lame.lame_set_num_channels(flags, 2);
        checkError(rc);        
        rc = Lame.lame_set_mode( flags, MPEG_mode.JOINT_STEREO );        
        checkError(rc);        
        rc = Lame.lame_set_in_samplerate(flags, 44100);
        checkError(rc);        

        rc = Lame.lame_set_out_samplerate(flags, 0); // LAME selects
        checkError(rc);        
        rc = Lame.lame_set_disable_reservoir(flags, 1);
        checkError(rc);        
        rc = Lame.lame_set_padding_type(flags, Padding_type.PAD_NO);
        checkError(rc);        
        rc = Lame.lame_set_bWriteVbrTag(flags, 1);        
        checkError(rc);        
        
        rc = Lame.lame_set_brate(flags,128);        
        checkError(rc);        
        rc = Lame.lame_set_quality(flags,2);        
        checkError(rc);        
        rc = Lame.lame_set_original(flags,1);
        checkError(rc);                         
                
        rc = Lame.lame_init_params(flags);        
        checkError(rc);        

/*             
        LameMsgCallbackTest cb = new LameMsgCallbackTest();
        
        Lame.lame_set_errorf(flags,cb);
        Lame.lame_set_debugf(flags,cb);
        Lame.lame_set_msgf(flags,cb);
                
        Lame.lame_print_config(flags);         
*/        
        int numChannels = Lame.lame_get_num_channels(flags); 
        
        // For MPEG-I, 1152 samples per frame per channel
        int mSamplesPerFrameAndChannel = 1152;        
        int mSamplesPerFrame = 1152 * numChannels;        
        
        // Worst case MPEG-I (see lame.h/lame_encode_buffer())
        int mOutBufferSize = mSamplesPerFrame * (320 / 8) / 8 + 4 * 1152 * (320 / 8) / 8 + 512;
        // int mOutBufferSize = (int)(1.25 * ( mSamplesPerFrame / Lame.lame_get_num_channels(flags) ) + 7200);
        
        
        // Allocate buffers
        //short[] pWAVBuffer = new short[mSamplesPerFrame];  
        byte[] pWAVBuffer = new byte[mSamplesPerFrame * 2]; // 2 bytes per short
        byte[] pMP3Buffer = new byte[mOutBufferSize];     
        
        
        // WAV file supposed 44100 Hz, Stereo, 16 bits
        BufferedInputStream wavStream = new BufferedInputStream(new FileInputStream(wavFile));       
        BufferedOutputStream mp3Stream = new BufferedOutputStream(new FileOutputStream(mp3File));
        
        wavStream.skip(44); // Skipping the WAV header        
        
        // Convert All PCM samples
        int read;        
        while ((read = wavStream.read(pWAVBuffer,0,pWAVBuffer.length)) > 0)
        {
            // Encode samples
            // read is "bytes", each sample is 2 bytes
            int readSamplesAllChannels = read / 2;             
            int readSamples = readSamplesAllChannels / numChannels;
            int nOutputBytes = Lame.lame_encode_buffer_interleaved(flags,pWAVBuffer,readSamples,pMP3Buffer,0);

            // write nOutputBytes bytes that are returned in the pMP3Buffer to disk
            mp3Stream.write(pMP3Buffer,0,nOutputBytes);
        }                
        
        int nOutputBytes = Lame.lame_encode_flush_nogap( flags, pMP3Buffer, 0 );        
	// Are there any bytes pending?
	// If so, write them to disk               
	if( nOutputBytes != 0 )
            mp3Stream.write(pMP3Buffer,0,nOutputBytes);
        
        mp3Stream.flush();
        
        wavStream.close();
        mp3Stream.close();         

        if (Lame.lame_get_bWriteVbrTag(flags) > 0)        
        {
            FileUtil.FILE fpStream = FileUtil.fopen( mp3File, "rb+" );

            Lame.lame_mp3_tags_fid( flags, fpStream );

            FileUtil.fclose( fpStream ); 
        }       

        Lame.lame_close(flags);   
                
        //Test.checkFiles(mp3File,mp3RefFile,false);
    }   
    
    public static void testOOPAPIStreamEncoder(String wavFile,String mp3File) throws Exception
    {         
        new File(mp3File).delete(); 
        
        LAMEOnJ lame = LAMEOnJBoot.get();
        LAMEOnJStdEncoder std = lame.getLAMEOnJStdEncoder();
        StreamEncoder encoder = std.createStreamEncoder(wavFile);
        
        EncoderConfig conf = encoder.getEncoderConfig();
        // Number of channels is detected by LAMEOnJ
        conf.setErrorProtection(true);        
        conf.setInSamplerate(44100);
        conf.setOutSamplerate(0);  // LAME selects    
        conf.setDisableReservoir(true);
        conf.setPaddingType(Padding_type.PAD_NO);
        conf.setBWriteVbrTag(true);
        conf.setBrate(128);
        conf.setQuality(2);
        conf.setOriginal(true);
        
        encoder.encode(mp3File);
        
        // Test.checkFiles(mp3File,mp3RefFile,false);        
    }           
    
    public static void testOOPAPIStreamEncoderProgressive(String wavFile,String mp3File) throws Exception
    {         
        new File(mp3File).delete(); 
        
        LAMEOnJ lame = LAMEOnJBoot.get();
        LAMEOnJStdEncoder std = lame.getLAMEOnJStdEncoder();
        StreamEncoder encoder = std.createStreamEncoder(wavFile);
        
        EncoderConfig conf = encoder.getEncoderConfig();
        conf.setErrorProtection(true);        
        conf.setNumChannels(2);
        conf.setMode(MPEG_mode.JOINT_STEREO);
        conf.setInSamplerate(44100);
        conf.setOutSamplerate(0);  // LAME selects    
        conf.setDisableReservoir(true);
        conf.setPaddingType(Padding_type.PAD_NO);
        conf.setBWriteVbrTag(true);
        conf.setBrate(128);
        conf.setQuality(2);
        conf.setOriginal(true);             
        
        
        int mOutBufferSize = encoder.initEncoding();
        byte[] mp3Buffer = new byte[mOutBufferSize];
        
        BufferedOutputStream mp3Stream = new BufferedOutputStream(new FileOutputStream(mp3File));
        
        int nOutputBytes;
        while( (nOutputBytes = encoder.encodeBuffer(mp3Buffer)) > 0 )
        {
            mp3Stream.write(mp3Buffer,0,nOutputBytes);
        }       
        
        mp3Stream.close();
        
        encoder.writeVbrTag(mp3File);
        
        encoder.close();
        
        // Test.checkFiles(mp3File,mp3RefFile,false); 
    }
    
    public static void testOOPAPIGenericEncoder(String wavFile,String mp3File) throws Exception    
    {
        new File(mp3File).delete(); 
        
        LAMEOnJ lame = LAMEOnJBoot.get();
        LAMEOnJStdEncoder std = lame.getLAMEOnJStdEncoder();        
        GenericEncoder encoder = std.createGenericEncoder();

        EncoderConfig conf = encoder.getEncoderConfig();
        conf.setErrorProtection(true);        
        conf.setNumChannels(2);
        conf.setMode(MPEG_mode.JOINT_STEREO);
        conf.setInSamplerate(44100);
        conf.setOutSamplerate(0);  // LAME selects    
        conf.setDisableReservoir(true);
        conf.setPaddingType(Padding_type.PAD_NO);
        conf.setBWriteVbrTag(true);
        conf.setBrate(128);
        conf.setQuality(2);
        conf.setOriginal(true);                      

        int[] pcmBufferSize = new int[1];
        int[] mp3BufferSize = new int[1];
        encoder.initEncoding(pcmBufferSize,mp3BufferSize);   

        int mBufferSize = pcmBufferSize[0];
        int mOutBufferSize = mp3BufferSize[0];
        
        // Allocate buffers
        byte[] pWAVBuffer = new byte[mBufferSize];
        byte[] pMP3Buffer = new byte[mOutBufferSize];            
        
        // WAV file supposed 44100 Hz, Stereo, 16 bits
        BufferedInputStream wavStream = new BufferedInputStream(new FileInputStream(wavFile));       
        BufferedOutputStream mp3Stream = new BufferedOutputStream(new FileOutputStream(mp3File));
        
        wavStream.skip(44); // Skipping the WAV header        
        
        // Convert All PCM samples
        int read;        
        while ((read = wavStream.read(pWAVBuffer,0,pWAVBuffer.length)) > 0)
        {
            // Encode samples
            // read is "bytes"            
            int nOutputBytes = encoder.encodeBuffer(pWAVBuffer,read,pMP3Buffer);
            
             // write nOutputBytes bytes that are returned in the pMP3Buffer to disk
            mp3Stream.write(pMP3Buffer,0,nOutputBytes);
        }                
        
        int nOutputBytes = encoder.encodeFlush(pMP3Buffer);        
	// Are there any bytes pending?
	// If so, write them to disk               
	if( nOutputBytes != 0 )
            mp3Stream.write(pMP3Buffer,0,nOutputBytes);
        
        mp3Stream.flush();
        
        wavStream.close();
        mp3Stream.close();         

        encoder.writeVbrTag(mp3File);

        encoder.close();   
                
        // Test.checkFiles(mp3File,mp3RefFile,false);
    }
    
           
    public static void checkError(int err)
    {
        if (err != lame_errorcodes_t.LAME_OKAY)
            throw new RuntimeException("ERROR " + err);     
    }       
}
