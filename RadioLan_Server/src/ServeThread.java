
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saumiko
 */
public class ServeThread implements Runnable{
    private Thread t;
   private String threadName;
   
   ServeThread( String name){
       Thread t = new Thread(this, "Server Thread");
        t.setPriority(2);
        t.start();
   }
   public void run() {
        try {
            //System.out.println("Running " +  threadName );
            Server.start();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServeThread.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

}
