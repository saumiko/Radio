/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shorot;

import java.io.*;
import javax.sound.sampled.*;
import java.net.*;
import java.util.*;

public class Circle extends Thread {

    private static final int DEFAULT_INTERNAL_BUFSIZ = 20480; //Audio Buffer Sizes
    private static final int DEFAULT_EXTERNAL_BUFSIZ = 20480;
    private TargetDataLine m_targetLine; //Audio In Line
    private boolean m_bRecording;
    private int m_nExternalBufferSize;
    static int PacketsOUT;
    static boolean goflag;
    int stepSizeTable[] = {
        7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 19, 21, 23, 25, 28, 31, 34,
        37, 41, 45, 50, 55, 60, 66, 73, 80, 88, 97, 107, 118, 130, 143,
        157, 173, 190, 209, 230, 253, 279, 307, 337, 371, 408, 449, 494,
        544, 598, 658, 724, 796, 876, 963, 1060, 1166, 1282, 1411, 1552,
        1707, 1878, 2066, 2272, 2499, 2749, 3024, 3327, 3660, 4026,
        4428, 4871, 5358, 5894, 6484, 7132, 7845, 8630, 9493, 10442,
        11487, 12635, 13899, 15289, 16818, 18500, 20350, 22385, 24623,
        27086, 29794, 32767};

    public Circle(AudioFormat format, int nInternalBufferSize, int nExternalBufferSize, //Gets Lines and Mixers
            String strMixerName) throws LineUnavailableException { //Ready

        Mixer mixer = null;
        if (strMixerName != null) {
            Mixer.Info mixerInfo = getMixerInfo(strMixerName);
            mixer = AudioSystem.getMixer(mixerInfo);
        }
        DataLine.Info InfotargetInfo = new DataLine.Info(TargetDataLine.class, format, nInternalBufferSize);
        if (mixer != null) {
            m_targetLine = (TargetDataLine) mixer.getLine(InfotargetInfo);
        } else {
            m_targetLine = (TargetDataLine) AudioSystem.getLine(InfotargetInfo);
        }
        m_targetLine.open(format, nInternalBufferSize);
        m_nExternalBufferSize = nExternalBufferSize;
    }

    public int getIndexAdjust(float multiplier) { //Returns the Index Adjustment 
        int adjust = -1; //According to the step size multiplier
        if (multiplier == 1.75f) {
            adjust = 8;
        } else if (multiplier == 1.5f) {
            adjust = 6;
        } else if (multiplier == 1.25f) {
            adjust = 4;
        } else if (multiplier == 1f) {
            adjust = 2;
        }
        return adjust;
    }

    public float[] getMultiplier(int sample, int stepSize) { //returns Step Size Multiplier
        int absSample = Math.abs(sample); //AND Quantization Output as a float
        float array[] = new float[2]; //it's cast to a byte later
        if (absSample < (.25 * stepSize)) {
            array[0] = 0;
            array[1] = 0;
        } else if (absSample < (.5 * stepSize)) {
            array[0] = 0.25f; //returns Multiplier
            array[1] = 1; //and Quantization Output
        } else if (absSample < (.75 * stepSize)) {
            array[0] = 0.5f;
            array[1] = 2;
        } else if (absSample < stepSize) {
            array[0] = 0.75f;
            array[1] = 3;
        } else if (absSample < (1.25 * stepSize)) {
            array[0] = 1f;
            array[1] = 4;
        } else if (absSample < (1.5 * stepSize)) {
            array[0] = 1.25f;
            array[1] = 5;
        } else if (absSample < (1.75 * stepSize)) {
            array[0] = 1.5f;
            array[1] = 6;
        } else {
            array[0] = 1.75f;
            array[1] = 7;
        }
        return array;
    }

    public byte[] Encode(byte[] data) { //takes the audio buffer and returns ten encoded packets
        byte encoded[] = new byte[82]; //will contain the encoded values
        int encodeCount = 2;
        int delta;
        int stepSize = 0;
        float[] stepMult;
        short Quant;
        int adjust; //index adjustment
        int index = 0; //Index starts at 0
        short last = 0; //contains the previous 16-bit value (sent value, not original)
        short current = 0; //contains the current 16-bit value
        byte newbyte = 0;
        boolean sign;
        encoded[0] = data[0]; //the first 16-bit sound is not encoded
        encoded[1] = data[1];
        current = data[0]; //the first 16-bit sound is turned into a 16-bit short
        current = (short) (255 & current);
        current = (short) (current << 8);
        current = (short) (current | (255 & data[1]));
        last = current;
        for (int i = 2; i < data.length; i += 2) { //the next 160 16-bit sounds are encoded into 4-bits
            current = 0; //each will share a byte with another sound. 
            current = data[i];
            current = (short) (255 & current);
            current = (short) (current << 8);
            current = (short) (current | (255 & data[i + 1]));
            delta = (current - last);
            sign = true; //true means greater than 0
            if (delta < 0) {
                sign = false; //false means less than 0, gets appended to Quant
            }
            delta = Math.abs(delta); //Absolute value of Delta
            stepSize = stepSizeTable[index]; //Gets the Step Size 
            stepMult = getMultiplier(delta, stepSize); //Multiplier
            adjust = getIndexAdjust(stepMult[0]); //Index Adjustment
            index = index + adjust; //Makes adjustment to index
            if (index < 0) {
                index = 0;
            }
            if (index > 88) {
                index = 88;
            }
            delta = Math.round(stepSize * stepMult[0]); //Gets the New Delta Value 
            if (!sign) {
                delta *= -1;
            } //Makes it negative if necessary
            last = (short) (last + (short) (delta)); //Save value as LAST 
            Quant = (short) stepMult[1]; //Set Quant as computer Quantization Output 
            if (!sign) {
                Quant = (short) ((byte) Quant | 8); //Makes 4th bit 1, if negative 
            }

            if ((i % 4) == 2) { //if it's even, shifts to right and stored 
                encoded[encodeCount] = (byte) ((Quant & 255) << 4);
            } else { //if it's odd, OR with the previous value
                encoded[encodeCount] = (byte) (encoded[encodeCount] | (255 & Quant));
                encodeCount++; //thus, two 4-bit values are stored in one byte
            }
        }
        return encoded; //return ENCODED array
    }

    public void start() { //Starts the Audio In Thread
        m_targetLine.start();
        super.start();
    }

    public void end() {
        goflag = false;
    }

    public void run() {
        try {
            byte[] abBuffer = new byte[322]; //buffer size of 161 2-byte samples 
            byte[] sendData = new byte[82]; //buffer size of 160 4-bit samples and 1 16-bit sample
            m_bRecording = true;
            DatagramSocket clientSocket = new DatagramSocket();//Starts the Sending Socket
            InetAddress IPAddress = InetAddress.getByName("localhost"); //Sets The IP ADDRESS 
            PacketsOUT = 0;
            while (m_bRecording && goflag) {
                abBuffer = new byte[322];
                sendData = new byte[82];
                m_targetLine.read(abBuffer, 0, 322); //Read in the samples 
                sendData = Encode(abBuffer); //Encode the samples 
                DatagramPacket sendPacket = new DatagramPacket(sendData, 82, IPAddress, 6001); //Send the Encoded Samples
                clientSocket.send(sendPacket); //161 samples as 82 bytes 
                PacketsOUT++;
                Thread.sleep(20); //Wait 20 milliseconds before sending again 

            }
            clientSocket.close(); //Close Socket
        } catch (Exception e) { //Try and Catch required for Thread.sleep()
            System.out.println("error");
        }
    }

    private static Mixer.Info getMixerInfo(String strMixerName) { //Returns Mixer info 
        Mixer.Info[] aInfos = AudioSystem.getMixerInfo(); //Used for setting up the Audio System
        for (int i = 0; i < aInfos.length; i++) {
            if (aInfos[i].getName().equals(strMixerName)) {
                return aInfos[i];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String strMixerName = null;
        float fFrameRate = 8000.0F; //8000 samples per second
        int nInternalBufferSize = DEFAULT_INTERNAL_BUFSIZ;
        int nExternalBufferSize = DEFAULT_EXTERNAL_BUFSIZ;
        AudioFormat audioFormat = new AudioFormat(fFrameRate, 16, 1, true, true);
        Circle audioLoop = null;
        goflag = true;
        long start = System.currentTimeMillis() / 1000;
        try {
            audioLoop = new Circle(audioFormat, nInternalBufferSize, nExternalBufferSize, //Sets up the Audio System
                    strMixerName);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        }
        audioLoop.start(); //Starts the Thread
        try {
            InputStreamReader input = new InputStreamReader(System.in);
            while (input.read() < 0) {
            }
            audioLoop.end();
            System.out.println("Packets sent = " + PacketsOUT);
            System.out.println("Samples sent = " + (PacketsOUT * 161));
            System.out.println("Time Elapsed = " + ((System.currentTimeMillis() / 1000) - start));
            System.out.println("Packets Per Second = " + (PacketsOUT / ((System.currentTimeMillis() / 1000) - start)));

        } catch (IOException e) {
        }
        System.exit(1);

    }

}
