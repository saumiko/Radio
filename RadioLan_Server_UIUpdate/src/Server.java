/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.xml.internal.ws.resources.ServerMessages;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asif Mohaimen
 */
public class Server {

    private ServerSocket serverSocket;
    Thread textIn, textOut, VoiceIn, voiceOut;
    Data data;
    String msg = null;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        //  serverSocket.setSoTimeout(10000);
    }

    Server() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void runServer() {
        Scanner serverScanner = new Scanner(System.in);
        AudioProcessor audioProcessor = new AudioProcessor();
//        AudioProcessor1 audioProcessor1 = new AudioProcessor1();
        try {
            System.out.println("SERVER SITE");
            System.out.println("Waiting for client on port " + serverSocket.getLocalPort() + ".");
            Socket server = serverSocket.accept();
            System.out.println("Connected to " + server.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(server.getInputStream());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());

            voiceOut = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        data = audioProcessor.readTargetLine();
//                audioProcessor.writeAudio();
                        try {
                            out.write(data.targetData, 0, data.numBytesRead);
//                            System.out.println("server : " + data.numBytesRead);
                        } catch (IOException ex) {
                            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            });
            textOut = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (serverScanner.hasNext()) {
                        try {
                            msg = serverScanner.nextLine();
                            if (msg.equalsIgnoreCase("exit")) {
                                out.writeUTF("Thank you for connecting to "
                                        + server.getLocalSocketAddress() + "\nGoodbye!");
                                break;
                            }
                            out.writeUTF(msg);
                        } catch (IOException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
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
                            System.out.println("Client: " + msg);
                        } catch (Exception ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
            });
            VoiceIn = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {

                            audioProcessor.readTargetLine();
                            in.read(audioProcessor.getTargetData(), 0, audioProcessor.getNumBytesRead());
                            audioProcessor.writeAudio();

                        } catch (Exception ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });

//            server.close();
        } catch (SocketTimeoutException s) {
            System.out.println("Socket timed out!");
            //  break;
        } catch (IOException e) {
            e.printStackTrace();
            //  break;
        }
        //  }
        //  }
    }

    public static void start() throws UnknownHostException {
        for(int port=1234; port<1237;port++){
        //int port = 1234;
        InetAddress IP=InetAddress.getLocalHost();
        RadioLanBroadCast.jLabel2.setText("IP:    "+IP.getHostAddress()+"                          Port:    "+Integer.toString(port));
        try {
            Server server = new Server(port);
            server.runServer();
            server.voiceOut.start();
//            server.textOut.start();
//            server.textIn.start();
//            server.VoiceIn.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }
}
