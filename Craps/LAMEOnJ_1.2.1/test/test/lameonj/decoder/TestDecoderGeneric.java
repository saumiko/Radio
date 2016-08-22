/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package test.lameonj.decoder;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import lame.std.mp3data_struct;
import lameonj.LAMEOnJ;
import lameonj.LAMEOnJBoot;
import lameonj.decoder.std.PCMFrame;
import lameonj.decoder.std.GenericDecoder;
import lameonj.decoder.std.LAMEOnJStdDecoder;

/**
 *
 * @author jmarranz
 */
public class TestDecoderGeneric
{
    public static void test(String mp3File,String wavFile) throws Exception
    {
        InputStream input = new BufferedInputStream(new FileInputStream(mp3File));
        OutputStream output = new BufferedOutputStream(new FileOutputStream(wavFile));

        LAMEOnJ lameOnJ = LAMEOnJBoot.get();
        LAMEOnJStdDecoder decoder = lameOnJ.getLAMEOnJStdDecoder();
        GenericDecoder decoderTask = decoder.createGenericDecoder();

        byte[] mp3buf = new byte[500];
        mp3data_struct headers;
        do
        {
            int size = input.read(mp3buf);
            if (size == 0) throw new RuntimeException("Unexpected end of file");
            headers = decoderTask.decodeHeaders(mp3buf,size);
        }
        while(headers == null);

        decoderTask.writeWAVHeader(output);

        PCMFrame frame;
        do
        {
            frame = getNextDecodedFrame(decoderTask,input);
            if (frame != null)
                decoderTask.writeDecodedFrame(frame,output);
        }
        while(frame != null);

        decoderTask.close();

        input.close();
        output.close();
        
        // If decoderTask.isMP3HeaderComplete() == true this call is not needed:        
        decoderTask.fixWAVHeader(wavFile);
    }

    public static PCMFrame getNextDecodedFrame(GenericDecoder decoderTask,InputStream input) throws IOException
    {
        PCMFrame frame = decoderTask.decodePendingFrame();
        if (frame == null)
        {
            byte[] mp3buf = new byte[1024];
            do
            {
                int size = input.read(mp3buf);
                if (size <= 0) // End of file
                    return decoderTask.decodePendingFrame();

                frame = decoderTask.decodeFrame(mp3buf,size);
            }
            while(frame == null);
            return frame;
        }
        else return frame;
    }

}
