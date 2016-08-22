/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asif Mohaimen
 */
import java.util.ArrayList;
import java.util.Date;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

public class TestMic {

    static int numBytesRead;
    static byte[] targetData; 
    static byte[] play;
    public static void main(String[] args) {
        AudioFormat format = new AudioFormat(44100, 16, 2, true, true);

        DataLine.Info targetInfo = new DataLine.Info(TargetDataLine.class, format);
        DataLine.Info sourceInfo = new DataLine.Info(SourceDataLine.class, format);

        try {
            TargetDataLine targetLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
            targetLine.open(format);
            targetLine.start();

            SourceDataLine sourceLine = (SourceDataLine) AudioSystem.getLine(sourceInfo);
            sourceLine.open(format);
            sourceLine.start();

            
            targetData = new byte[targetLine.getBufferSize() / 5];
            play = new byte[targetLine.getBufferSize()*50];

//			while (true) {
//				numBytesRead = targetLine.read(targetData, 0, targetData.length);
//
//				if (numBytesRead == -1)	break;
//
//				sourceLine.write(targetData, 0, numBytesRead);
//			}
            new Thread(new Runnable() {
                @Override
                public void run() {
//                                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    long curTime = System.currentTimeMillis();
                    int pos = 0;
                    while (System.currentTimeMillis() - curTime <= 7000) {
                        numBytesRead = targetLine.read(targetData, 0, targetData.length);
                        for(int i=0;i<targetData.length;i++){
                            play[pos++] = targetData[i];
                        }
                        
                    }
                    sourceLine.write(play, 0, play.length);
                }
            }).start();
     
        } catch (Exception e) {
            System.err.println(e);
        }
    }

}
