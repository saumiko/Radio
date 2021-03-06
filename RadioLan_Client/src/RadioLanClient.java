/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author saumiko
 */
public class RadioLanClient extends javax.swing.JFrame {

    /**
     * Creates new form RadioLanClient
     */
    public RadioLanClient() {
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

        ClientUIPanel = new javax.swing.JPanel();
        ip = new javax.swing.JTextField();
        port = new javax.swing.JTextField();
        Listen = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RadioLan");
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        ClientUIPanel.setPreferredSize(new java.awt.Dimension(500, 250));
        ClientUIPanel.setLayout(null);

        ip.setBackground(new java.awt.Color(83, 83, 83));
        ip.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        ip.setForeground(new java.awt.Color(255, 255, 255));
        ip.setText("192.168.0.0");
        ip.setToolTipText("");
        ClientUIPanel.add(ip);
        ip.setBounds(140, 70, 140, 26);

        port.setBackground(new java.awt.Color(83, 83, 83));
        port.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        port.setForeground(new java.awt.Color(255, 255, 255));
        port.setText("1234");
        port.setToolTipText("");
        port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                portActionPerformed(evt);
            }
        });
        ClientUIPanel.add(port);
        port.setBounds(380, 70, 57, 26);

        Listen.setBackground(new java.awt.Color(255, 255, 255));
        Listen.setFont(new java.awt.Font("Impact", 1, 18)); // NOI18N
        Listen.setText("►");
        Listen.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Listen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListenActionPerformed(evt);
            }
        });
        ClientUIPanel.add(Listen);
        Listen.setBounds(210, 180, 80, 32);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Radio.png"))); // NOI18N
        ClientUIPanel.add(jLabel4);
        jLabel4.setBounds(0, 0, 500, 250);
        jLabel4.getAccessibleContext().setAccessibleName("PanelLabel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ClientUIPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ClientUIPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void portActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_portActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_portActionPerformed

    private void ListenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListenActionPerformed
        // TODO add your handling code here:
        String IP = "localhost";
        String ports="nothing";
        try{
            IP = this.ip.getText();
            ports = this.port.getText();
        }
        catch(Exception e){}
        ClientThread t = new ClientThread(IP, ports);
    }//GEN-LAST:event_ListenActionPerformed

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
            java.util.logging.Logger.getLogger(RadioLanClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RadioLanClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RadioLanClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RadioLanClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RadioLanClient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ClientUIPanel;
    private javax.swing.JButton Listen;
    private javax.swing.JTextField ip;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField port;
    // End of variables declaration//GEN-END:variables
}
