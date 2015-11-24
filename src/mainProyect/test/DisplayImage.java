/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProyect.test;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

import javax.swing.filechooser.*;
import mainProyect.sql.ConectarSql;

/**
 *
 * @author cmch05
 */
public class DisplayImage extends javax.swing.JFrame {
    
    ConectarSql con = new ConectarSql("root", "", "localhost", "imagenes");
    
     File fiechero;
     FileInputStream fis=null;
     PreparedStatement pst=null;
     
    /**
     * Creates new form FileChois
     */
    public DisplayImage() {
        initComponents();
    }
    
    public void seleccionarImagen(){
        DisplayImage ventana = new DisplayImage();
        //resultado debuelve 0 si se escoje un archivo y 1 si se cancela
        //o no se seleccionana nada
         int resultado;
        
        //filtro de extenciones
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG Y PNG", "jpg","npg","jpeg");
        //aplicamos el filtro al fileChooser
        ventana.flcArchivo.setFileFilter(filtro);
        
        resultado=  ventana.flcArchivo.showOpenDialog(null);
        //approve_option es un entero 1
        if (JFileChooser.APPROVE_OPTION==resultado) {
            fiechero=ventana.flcArchivo.getSelectedFile();
            
             try {
                 fis= new FileInputStream(fiechero);
             } 
             catch (FileNotFoundException ex) {
                 Logger.getLogger(DisplayImage.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            
                    
            try {
                ImageIcon icon= new ImageIcon(fiechero.toString());
                
                Icon icono = new ImageIcon(icon.getImage().getScaledInstance(
                        lblFoto.getWidth(), lblFoto.getHeight(),Image.SCALE_DEFAULT));
                //eliminamos el texto que tenga el labal
                lblFoto.setText(null);
                lblFoto.setIcon(icono);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error:"  +e);
            }
        }
        // JOptionPane.showMessageDialog(null, resultado);
    }
    public void guardarImagenDb(){
        // TODO add your handling code here:
        String sql="insert into usuario values(?,?)";
        try {
            pst = con.coneccion().prepareStatement(sql);
            
            pst.setString(1, txtNombre.getText());
            pst.setBinaryStream(2, fis, (int) fiechero.length());
            pst.executeUpdate();
        } 
        catch (SQLException ex) {
            Logger.getLogger(DisplayImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        JOptionPane.showMessageDialog(null, fiechero.length());
    }
    
    public void cargarImagenDb(){
        String sql="select * from usuario where nombre='asia'";
        try {
            pst = con.coneccion().prepareStatement(sql);
            ResultSet rs= pst.executeQuery();
            
            
            while(rs.next()){
                String name = rs.getString(1);
                File image = new File("neko.jpeg");
                FileOutputStream fos = new FileOutputStream(image);
                
                
                byte[] buffer =new byte[1];
                InputStream is = rs.getBinaryStream(2);
                
                while(is.read(buffer)>0){
                    
                    fos.write(buffer);
                    
                }
                
                fos.close();
                
                ///convertir tipo File a icono *************
                ImageIcon icon= new ImageIcon(image.toString());
                Icon icono = new ImageIcon(icon.getImage().getScaledInstance(
                        lblFoto.getWidth(), lblFoto.getHeight(),Image.SCALE_DEFAULT));
                //eliminamos el texto que tenga el labal
                lblFoto.setText(null);
                lblFoto.setIcon(icono);
                //*******************************************
                JOptionPane.showMessageDialog(null, "erraaaaaaaaaaaaaaaaaaaaaaaaor:   "+ name);
            }
            
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(DisplayImage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DisplayImage.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DisplayImage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        flcArchivo = new javax.swing.JFileChooser();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblFoto = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        btnCargar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Vista previa");

        jButton1.setText("abrir Imagen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addComponent(txtNombre))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton2.setText("Guardar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnCargar.setText("Cargar");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(29, 29, 29))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(btnCargar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(btnCargar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    
   
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
       seleccionarImagen();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        guardarImagenDb();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        // TODO add your handling code here:
        cargarImagenDb();
    }//GEN-LAST:event_btnCargarActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DisplayImage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DisplayImage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DisplayImage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DisplayImage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DisplayImage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargar;
    private javax.swing.JFileChooser flcArchivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
