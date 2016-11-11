
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asif Mohaimen
 */
public class AudioProcessor {

    int numBytesRead;
    byte[] targetData;
    AudioFormat format;
    DataLine.Info targetInfo;
    DataLine.Info sourceInfo;
    TargetDataLine targetLine;
    SourceDataLine sourceLine;

    public int getNumBytesRead() {
        return numBytesRead;
    }

    public void setNumBytesRead(int numBytesRead) {
        this.numBytesRead = numBytesRead;
    }

    public byte[] getTargetData() {
        return targetData;
    }

    public void setTargetData(byte[] targetData) {
        this.targetData = targetData;
    }
    Data data;
    public AudioProcessor() {

         format = new AudioFormat(44100, 16, 2, true, true);
         targetInfo = new DataLine.Info(TargetDataLine.class, format);
         sourceInfo = new DataLine.Info(SourceDataLine.class, format);
         data = new Data();
        try {
             targetLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
            targetLine.open(format);
            targetLine.start();

            sourceLine = (SourceDataLine) AudioSystem.getLine(sourceInfo);
            sourceLine.open(format);
            sourceLine.start();
            targetData = new byte[targetLine.getBufferSize() / 5];
    
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
   public Data readTargetLine(){
       data.setNumBytesRead(targetLine.read(targetData, 0, targetData.length));
       numBytesRead = data.getNumBytesRead();
       data.setTargetData(targetData);
       return data;
   }
   public void writeAudio(){
      sourceLine.write(targetData, 0, numBytesRead);
   }
}
