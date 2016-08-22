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
      //System.out.println("Running " +  threadName );
      Server.start();
   }

}
