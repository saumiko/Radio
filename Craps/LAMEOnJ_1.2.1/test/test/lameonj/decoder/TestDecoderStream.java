/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.lameonj.decoder;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import lame.std.mp3data_struct;
import lameonj.LAMEOnJ;
import lameonj.LAMEOnJBoot;
import lameonj.decoder.std.PCMFrame;
import lameonj.decoder.std.LAMEOnJStdDecoder;
import lameonj.decoder.std.StreamDecoder;

/**
 *
 * @author jmarranz
 */
public class TestDecoderStream
{
    public static void test1(String mp3File,String wavFile) throws Exception
    {
        OutputStream output = new BufferedOutputStream(new FileOutputStream(wavFile));

        LAMEOnJ lameOnJ = LAMEOnJBoot.get();
        LAMEOnJStdDecoder decoder = lameOnJ.getLAMEOnJStdDecoder();
        StreamDecoder decoderTask = decoder.createStreamDecoder(mp3File);

        mp3data_struct headers = decoderTask.decodeHeaders();

        decoderTask.writeWAVHeader(output);

        PCMFrame frame;
        do
        {
            frame = decoderTask.decodeFrame();
            if (frame != null)
                decoderTask.writeDecodedFrame(frame,output);
        }
        while(frame != null);

        decoderTask.close();

        output.close();
        
        // If decoderTask.isMP3HeaderComplete() == true this call is not needed:        
        decoderTask.fixWAVHeader(wavFile);
    }
    
    public static void test2(String mp3File,String wavFile) throws Exception
    {
        LAMEOnJ lameOnJ = LAMEOnJBoot.get();
        LAMEOnJStdDecoder decoder = lameOnJ.getLAMEOnJStdDecoder();
        StreamDecoder decoderTask = decoder.createStreamDecoder(mp3File);

        decoderTask.decode(wavFile);       
    }    
}
