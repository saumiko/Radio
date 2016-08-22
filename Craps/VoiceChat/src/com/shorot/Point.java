/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shorot;

/**
 *
 * @author Noymul Islam
 */
import javax.sound.sampled.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Point extends Thread {

    private static final int DEFAULT_INTERNAL_BUFSIZ = 20480; //Audio Buffer Sizes 
    private static final int DEFAULT_EXTERNAL_BUFSIZ = 20480;
    private SourceDataLine m_sourceLine; //Line for Audio Out
    private boolean m_bRecording;
    private int m_nExternalBufferSize;
    static boolean goflag;
    static int PacketsIN;

    int stepSizeTable[] = { //Step Size Table
        7, 8, 9, 10, 11, 12, 13, 14, 16, 17, 19, 21, 23, 25, 28, 31, 34,
        37, 41, 45, 50, 55, 60, 66, 73, 80, 88, 97, 107, 118, 130, 143,
        157, 173, 190, 209, 230, 253, 279, 307, 337, 371, 408, 449, 494,
        544, 598, 658, 724, 796, 876, 963, 1060, 1166, 1282, 1411, 1552,
        1707, 1878, 2066, 2272, 2499, 2749, 3024, 3327, 3660, 4026,
        4428, 4871, 5358, 5894, 6484, 7132, 7845, 8630, 9493, 10442,
        11487, 12635, 13899, 15289, 16818, 18500, 20350, 22385, 24623,
        27086, 29794, 32767};

    public Point(AudioFormat format, int nInternalBufferSize, int nExternalBufferSize, //Sets Up Audio System
            String strMixerName) throws LineUnavailableException {
        Mixer mixer = null;
        if (strMixerName != null) {
            Mixer.Info mixerInfo = getMixerInfo(strMixerName);
            mixer = AudioSystem.getMixer(mixerInfo);
        }
        DataLine.Info InfosourceInfo = new DataLine.Info(SourceDataLine.class, format, nInternalBufferSize);
        if (mixer != null) {
            m_sourceLine = (SourceDataLine) mixer.getLine(InfosourceInfo);
        } else {
            m_sourceLine = (SourceDataLine) AudioSystem.getLine(InfosourceInfo);
        }
        m_sourceLine.open(format, nInternalBufferSize);
        m_nExternalBufferSize = nExternalBufferSize;
    }

    public int getIndexAdjust(float multiplier) { //Returns Index Adjustment according to 
        int adjust = -1; //the step size multiplier
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

    public float getMultiplier(short Quantization) { //Returns the Step Size Multiplier
        if (Quantization == 0) { //According to the 4-bits received per sample
            return 0;
        } else if (Quantization == 1) {
            return 0.25f;
        } else if (Quantization == 2) {
            return 0.5f;
        } else if (Quantization == 3) {
            return 0.75f;
        } else if (Quantization == 4) {
            return 1.0f;
        } else if (Quantization == 5) {
            return 1.25f;
        } else if (Quantization == 6) {
            return 1.50f;
        } else {
            return 1.75f;
        }
    }

    public void start() { //Starts the Audio Thread
        m_sourceLine.start();
        super.start(); //Receiving and Decoding
    }

    public byte[] Decode(byte[] encoded) { //Decoding Method
        byte data[] = new byte[322]; //322 byte array to hold decoded samples
        short current; //Current Sample being computed
        short last; //Previous Sample
        short code; //4 bit sample
        int index = 0; //Index starts at 0
        float stepMult; //Step Size Multiplier
        int adjust; //Index Adjustment
        int delta; //Sample Delta
        int stepSize; //Step Size
        int datacount = 2;
        boolean sign;
        current = encoded[0]; //the first 16-bit sound is turned into a 16-bit short
        current = (short) (255 & current);
        current = (short) (current << 8);
        current = (short) (current | (255 & encoded[1]));
//System.out.println("FIRST CURRENT = "+current);
        last = current; //set last to first sample
        data[0] = encoded[0];
        data[1] = encoded[1];
        for (int i = 2; i < encoded.length; i++) { //each cycle should decode 2 16-bit sounds from a byte 
            sign = false;
            datacount = (4 * (i - 1)) - 2; //formula for getting the right nodes in the data array from i
            code = encoded[i]; //first half of the byte 
            code = (short) (code & 255);
            code = (short) (code >> 4); //shifts left half to right half
            code = (short) (code & 15); //assures the left half is 0's 
            if (code > 7) { //gets the sign of the delta (positive or negative)
                sign = true; //if negative, sign = true 
                code = (short) ((byte) code & 7);
            } else {
                sign = false;
            }
            stepMult = getMultiplier(code); //Uses the 4-bits to get the Step Size Multiplier 
            adjust = getIndexAdjust(stepMult); //And the Index Adjustment 
            stepSize = stepSizeTable[index]; //Uses the index to get the step size 
            delta = Math.round(stepSize * stepMult);//Sets Delta 
            if (sign) {
                delta *= -1;
            } //Sets Delta to Negative if necessary 
            current = (short) (last + (short) delta); //Sets Current sample to sum of previous and delta
            data[datacount] = (byte) ((current >> 8) & 255); //Splits the 16-bit short into two bytes 
            data[datacount + 1] = (byte) (current & 255); //Sets them into the array 
            index = index + adjust; //Updates the Index
            if (index < 0) {
                index = 0;
            }
            if (index > 88) {
                index = 88;
            }
            last = current; //Sets Last to Current
//second half of the byte
            code = encoded[i]; //Repeats the same thing using the
            code = (short) (code & 255); //right half of the byte 
            code = (short) (code & 15);
            if (code > 7) {
                sign = true;
                code = (short) ((byte) code & 7);
            } else {
                sign = false;
            }
            stepMult = getMultiplier(code);
            adjust = getIndexAdjust(stepMult);
            stepSize = stepSizeTable[index];
            delta = Math.round(stepSize * stepMult);
            if (sign) {
                delta *= -1;
            }
            current = (short) (last + (short) delta);
            data[datacount + 2] = (byte) ((current >> 8) & 255);
            data[datacount + 3] = (byte) (current & 255);
            index = index + adjust;
            if (index < 0) {
                index = 0;
            }
            if (index > 88) {
                index = 88;
            }
            last = current;
        }
        return data; //Returns the decompressed data array
    }

    public void end() {
        goflag = false;
    }

    public void run() {
        try {
            DatagramSocket serverSocket = new DatagramSocket(6001); //Opens the Receiving Socket 
            byte[] abBuffer = new byte[82]; //Receiving array
            byte[] decoded = new byte[322]; //Decompressed Array 
            m_bRecording = true;
            PacketsIN = 0;
            while (m_bRecording & goflag) {
                abBuffer = new byte[82];
                decoded = new byte[322];
                DatagramPacket receivePacket = new DatagramPacket(abBuffer, 82); //Creates the Packet
                serverSocket.receive(receivePacket); //Fills it from Server
                decoded = Decode(receivePacket.getData()); //Decoded the packet's Data 
                m_sourceLine.write(decoded, 0, decoded.length); //And writes the data to the 
                PacketsIN++;
            } //audio out line
        } catch (Exception e) {
            System.out.println("Receiving Error");
            System.exit(1);
        }
    }

    private static Mixer.Info getMixerInfo(String strMixerName) { //Mixer Info for 
        Mixer.Info[] aInfos = AudioSystem.getMixerInfo(); //Setting up the audio system
        for (int i = 0; i < aInfos.length; i++) {
            if (aInfos[i].getName().equals(strMixerName)) {
                return aInfos[i];
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        String strMixerName = null;
        float fFrameRate = 8000.0F; //8000 samples per sec
        int nInternalBufferSize = DEFAULT_INTERNAL_BUFSIZ; //sets audio buffers
        int nExternalBufferSize = DEFAULT_EXTERNAL_BUFSIZ;
        goflag = true;
        AudioFormat audioFormat = new AudioFormat(fFrameRate, 16, 1, true, true);//sets audio format
//8000 samples per second on Signed PCM and bigEndian format
        Point audioLoop = null;
        long start = System.currentTimeMillis() / 1000;
        try {
            audioLoop = new Point(audioFormat, nInternalBufferSize, nExternalBufferSize,//sets the audio system up
                    strMixerName);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            System.exit(1);
        }
        audioLoop.start(); //Starts the Receiving Thread, and Decoding and Audio
        try {
            InputStreamReader input = new InputStreamReader(System.in);
            while (input.read() < 0) {
            }
            audioLoop.end();
            System.out.println("Packets received = " + PacketsIN);
            System.out.println("Samples received = " + (PacketsIN * 161));
            System.out.println("Time Elapsed = " + ((System.currentTimeMillis() / 1000) - start));
            System.out.println("Packets Per Second = " + (PacketsIN / ((System.currentTimeMillis() / 1000) - start)));
        } catch (IOException e) {
        }
        System.exit(1);
    }
}
