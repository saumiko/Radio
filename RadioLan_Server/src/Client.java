/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asif Mohaimen
 */
public class Client {

    Thread textIn, textOut, voiceIn, voiceOut;
    Data data;
    String msg = null;
    Scanner clientScanner;

    public void runClient() {
        String serverName = "localhost";
        int port = 1234;
        AudioProcessor audioProcessor = new AudioProcessor();
        try {
            System.out.println("CLIENT SITE");
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);
            System.out.println("Just connected to "
                    + client.getRemoteSocketAddress());

            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            DataInputStream in = new DataInputStream(client.getInputStream());
            clientScanner = new Scanner(System.in);

            voiceIn = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            audioProcessor.readTargetLine();
                            in.read(audioProcessor.getTargetData(), 0, audioProcessor.getNumBytesRead());
//                            System.out.println("client :" + audioProcessor.getNumBytesRead());
                            audioProcessor.writeAudio();
                        } catch (IOException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            textIn = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(400);
                            msg = in.readUTF();
                            if (msg.equalsIgnoreCase("exit")) {
                                break;
                            }
                            System.out.println("Server: " + msg);
                        } catch (Exception ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });

            textOut = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (clientScanner.hasNextLine()) {
                        try {
                            msg = clientScanner.nextLine();
                            if (msg.equalsIgnoreCase("exit")) {
                                break;
                            }
                            out.writeUTF(msg);
                        } catch (IOException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            voiceOut = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        data = audioProcessor.readTargetLine();
                        try {
                            out.write(data.targetData, 0, data.numBytesRead);
                        } catch (IOException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
//            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        int i;
        //for(i=0;i<2;i++){
            Client client = new Client();
            client.runClient();
            client.voiceIn.start();
        //}
//        client.textIn.start();
//        client.textOut.start();
//        client.voiceOut.start();

    }
}
