/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Asif Mohaimen
 */
public class LanStreamingInJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("saumiko");
        AudioProcessor ap = new AudioProcessor();
        while(true){
            ap.readTargetLine();
            ap.writeAudio();
        }
    }
    
}
