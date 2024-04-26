package chat_tcp;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author 2200593@ifto.local
 */
public class TelaCliente extends javax.swing.JFrame {
    Cliente cliente;
    TelaChat telaChat;
    /**
     * Creates new form TelaCliente
     */
    public TelaCliente() {
        
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TelaServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TelaServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TelaServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        initComponents();
        
        ImageIcon icone = new ImageIcon("assets/cliente/usuarioLogin.png");
        icone.setImage(icone.getImage().getScaledInstance(JLabel_iconUserLogin.getWidth(), JLabel_iconUserLogin.getHeight(),1));
        JLabel_iconUserLogin.setIcon(icone);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        porta = new javax.swing.JTextField();
        ip = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        login = new javax.swing.JTextField();
        JLabel_iconUserLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jButton1.setBackground(new java.awt.Color(43, 134, 167));
        jButton1.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(60, 310, 120, 30);

        jButton2.setBackground(new java.awt.Color(249, 119, 119));
        jButton2.setFont(new java.awt.Font("sansserif", 1, 16)); // NOI18N
        jButton2.setText("Cancelar");
        jButton2.setMaximumSize(new java.awt.Dimension(61, 23));
        jButton2.setMinimumSize(new java.awt.Dimension(61, 23));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(230, 310, 120, 30);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Servidor"));
        jPanel1.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setFont(new java.awt.Font("Serif", 1, 16)); // NOI18N
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel1.setText("Porta:");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(100, 70, 70, 18);

        jLabel2.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel2.setText("Endereço IP:");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(50, 40, 120, 18);
        jPanel1.add(porta);
        porta.setBounds(170, 70, 140, 23);
        jPanel1.add(ip);
        ip.setBounds(170, 40, 140, 23);

        jLabel3.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel3.setText("Nome do Usuário:");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(10, 100, 160, 18);
        jPanel1.add(login);
        login.setBounds(170, 100, 140, 23);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(50, 140, 330, 140);
        jPanel1.getAccessibleContext().setAccessibleName("Dados de Conexão");
        jPanel1.getAccessibleContext().setAccessibleDescription("Dados de Conexão");

        getContentPane().add(JLabel_iconUserLogin);
        JLabel_iconUserLogin.setBounds(170, 20, 90, 90);

        setBounds(0, 0, 432, 399);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     
        try {
            
            int port = Integer.parseInt(porta.getText());
            Usuario usuario = new Usuario(port,ip.getText(),login.getText());
            cliente = new Cliente(usuario);
            
            cliente.conectar();
            JOptionPane.showMessageDialog(null, "Cliente Conectado!");
                
            telaChat = new TelaChat(this,true,cliente);
            telaChat.setVisible(true);  
            
        } catch (IOException ex) {
             JOptionPane.showMessageDialog(null, ex);
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JLabel_iconUserLogin;
    private javax.swing.JTextField ip;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField login;
    private javax.swing.JTextField porta;
    // End of variables declaration//GEN-END:variables
}
