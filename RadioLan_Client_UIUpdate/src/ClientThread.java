/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saumiko
 */
public class ClientThread implements Runnable{
    private Thread t;
   private String threadName;
   private String IP;
   private String port;
   
   ClientThread(String IPf, String portf){ 
       Thread t = new Thread(this, "Client Thread");
       this.IP = IPf;
       this.port = portf;
        t.setPriority(2);
        t.start();
   }
   public void run() {
      //System.out.println("Running " +  threadName );
      Client.start(IP, port);
   }
}
