
import java.net.InetAddress;
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
public class RadioLanBroadCast extends javax.swing.JFrame {

    /**
     * Creates new form RadioLanBroadCast
     */
    public RadioLanBroadCast() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BroadCastUIPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Start = new javax.swing.JButton();
        Stop = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(500, 333));
        setSize(new java.awt.Dimension(500, 333));

        BroadCastUIPanel.setMaximumSize(new java.awt.Dimension(500, 333));
        BroadCastUIPanel.setMinimumSize(new java.awt.Dimension(500, 333));
        BroadCastUIPanel.setPreferredSize(new java.awt.Dimension(500, 333));
        BroadCastUIPanel.setSize(new java.awt.Dimension(500, 333));
        BroadCastUIPanel.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("IP:    ---.---.-.-                          Port:    ----");
        BroadCastUIPanel.add(jLabel2);
        jLabel2.setBounds(70, 280, 380, 31);

        Start.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        Start.setText("►");
        Start.setMaximumSize(new java.awt.Dimension(75, 50));
        Start.setMinimumSize(new java.awt.Dimension(75, 50));
        Start.setPreferredSize(new java.awt.Dimension(75, 50));
        Start.setSize(new java.awt.Dimension(75, 50));
        Start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartActionPerformed(evt);
            }
        });
        BroadCastUIPanel.add(Start);
        Start.setBounds(90, 120, 75, 50);

        Stop.setFont(new java.awt.Font("Lucida Grande", 0, 40)); // NOI18N
        Stop.setText("■");
        Stop.setMaximumSize(new java.awt.Dimension(75, 50));
        Stop.setMinimumSize(new java.awt.Dimension(75, 50));
        Stop.setPreferredSize(new java.awt.Dimension(75, 50));
        Stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StopActionPerformed(evt);
            }
        });
        BroadCastUIPanel.add(Stop);
        Stop.setBounds(90, 180, 75, 50);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/broadcast.jpg"))); // NOI18N
        BroadCastUIPanel.add(jLabel3);
        jLabel3.setBounds(0, 0, 500, 333);
        BroadCastUIPanel.add(jLabel1);
        jLabel1.setBounds(250, 166, 0, 0);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(BroadCastUIPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BroadCastUIPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void StopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StopActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_StopActionPerformed

    private void StartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartActionPerformed
        try {
            // TODO add your handling code here:
            InetAddress IP=InetAddress.getLocalHost();
            //jLabel2.setText("IP: "+IP.getHostAddress()+"  Port: "+"1234");
            //jLabel2.setText("IP:    "+"---.---.-.-"+"                          Port:    "+"----");
        } 
        catch (UnknownHostException ex) {
            Logger.getLogger(RadioLanBroadCast.class.getName()).log(Level.SEVERE, null, ex);
        }
        ServeThread s = new ServeThread("1");
        
        //Server.start();
        //Client.Start();
    }//GEN-LAST:event_StartActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RadioLanBroadCast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RadioLanBroadCast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RadioLanBroadCast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RadioLanBroadCast.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RadioLanBroadCast().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BroadCastUIPanel;
    private javax.swing.JButton Start;
    private javax.swing.JButton Stop;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
