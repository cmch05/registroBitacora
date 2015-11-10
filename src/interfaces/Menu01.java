/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mundo.ConectarDB;
import javax.swing.JMenuItem;
import mundo.MetodosMenu01;
import org.apache.commons.codec.digest.DigestUtils;// modulo common

/**
 *
 * @author cmch05
 */
public class Menu01 extends javax.swing.JFrame {
    private int perfil;
    private DefaultTableModel modelo;
    private ArrayList serie=new ArrayList();
    private ArrayList nombreMenuItem=new ArrayList();
    private String stringSeleccion,sSQL;
    private String accionCrearActualizar;

    public Menu01(int perfil) {
        this.setResizable(false);
        this.perfil = perfil;
        initComponents();

        cargarJMenuItem();
        antesCerrar();
        desHabilitar();
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }
    boolean suiche = false;

    public Menu01() {
        setResizable(false);
        initComponents();

        //cargarCombo();
        //antesCerrar();
        //cboUsuario.setEnabled(false);
       // desHabilitar();
    }

    // previene la aslida y despues escucha el boton de salida y le dice que haga algo
    public void antesCerrar() {
        setDefaultCloseOperation(Menu01.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                registarSalida();
            }

        });

        // setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    public void registarSalida() {
        ConectarDB conectar = new ConectarDB();
        Connection con = conectar.coneccion();
        int ser = 0;
        String sSQL = "select serial from bitacora "
                + "order by serial desc limit 1 ";
        try {
            PreparedStatement pst = con.prepareStatement(sSQL);
            ResultSet rs = pst.executeQuery(sSQL);
            while (rs.next()) {
                ser = rs.getInt("serial");

            }
           JOptionPane.showMessageDialog(null, ser);
            sSQL = "update bitacora set fecha_salida = curdate() , hora_salida = curtime() "
                    + "where serial ='" + ser + "' ";
            //pst= con.prepareStatement(sSQL);
            pst.executeUpdate(sSQL);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error " + ex);
            //Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        //this.dispose();
        if (suiche == true) {
            this.dispose();
            Login login = new Login();
            login.setVisible(suiche);
        } else {

            System.exit(0);
        }
    }

    public void cargarJMenuItem() {
        //JOptionPane.showMessageDialog(null, perfil);
        ConectarDB coneccion = new ConectarDB();
        String contador = "";
         Connection con = coneccion.coneccion();
        
        sSQL = "select distinct permiso from perfil_permiso left join permiso on "
                + "perfil_permiso.id_perfil='"+perfil+"'and  perfil_permiso.id_permiso=permiso.id";
        
        
        //select permiso from perfil_permiso left join permiso on perfil_permiso.id_perfil='2'and  perfil_permiso.id_permiso=permiso.id;
        try {
            PreparedStatement pst = con.prepareStatement(sSQL);
            ResultSet rs = pst.executeQuery(sSQL);
            int i=0;
            while (rs.next()) {
                if(rs.getString("permiso")!=null){
                
                mPermiso.add(rs.getString("permiso")+" usuario");
                   agregarAccionMenu(mPermiso.getItem(i));
                   nombreMenuItem.add(mPermiso.getItem(i).getText());
               // cboTablas.addItem(rs.getString("id_perfil"));
               
               //JOptionPane.showMessageDialog(null, mPermiso.getItem(i).getText());
               i++;
                }
            }
            
              //JOptionPane.showMessageDialog(null, "exito ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error " + ex);
            //Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void agregarAccionMenu(JMenuItem m){
        
        m.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
                if(m.getText().equals("ver usuario")){
                    txtContraseña.setEnabled(false);
                        txtFecha.setEnabled(false);
                        txtNivel.setEnabled(false);
                        txtNombre.setEnabled(false);
                        btnCrear.setEnabled(false);
                        txtBuscar.setEnabled(true);
                        btnBitacora.setEnabled(true);
                        btnBuscar.setEnabled(true);
                }
                else if(m.getText().equals("crear usuario")){
                    
                        txtContraseña.setEnabled(true);
                        txtFecha.setEnabled(true);
                        txtNivel.setEnabled(true);
                        txtNombre.setEnabled(true);
                        btnCrear.setEnabled(true);
                        txtBuscar.setEnabled(true);
                        btnBitacora.setEnabled(true);
                        btnBuscar.setEnabled(true);
                        accionCrearActualizar="crear";
                    
                }
                else if(m.getText().equals("actualizar usuario")){
                    
                    
                        txtContraseña.setEnabled(true);
                        txtFecha.setEnabled(true);
                        txtNivel.setEnabled(true);
                        txtNombre.setEnabled(true);
                        btnCrear.setEnabled(true);
                        txtBuscar.setEnabled(true);
                        btnBitacora.setEnabled(true);
                        btnBuscar.setEnabled(true);
                        accionCrearActualizar="actualizar";
                }
            }
        });
    }
   
   
    public void ejecutarAccion(){
        String Mselct =(String) mPermiso.getItem(mPermiso.getWidth()).getText();
        JOptionPane.showMessageDialog(null, Mselct);
    }
    public void desHabilitar(){
        // txtBuscar.setEnabled(false);
        txtContraseña.setEnabled(false);
        txtFecha.setEnabled(false);
        txtNivel.setEnabled(false);
        txtNombre.setEnabled(false);
        btnCrear.setEnabled(false);
        txtBuscar.setEnabled(false);
        btnBitacora.setEnabled(false);
        btnBuscar.setEnabled(false);
        
    }
    public void habilitarActualizarCrear(){
        
    }
    public void habilitarVerBorrar(){
    
    }
   
    public void borrar(){
        txtBuscar.setText("");
        txtContraseña.setText("");
        txtFecha.setText("");
        txtNivel.setText("");
        txtNombre.setText("");
        
        // tblUsuario.setModel(new DefaultTableModel());
        
    }
    
    public void crearUsuario() {
        ConectarDB conectar = new ConectarDB();
        Connection con = conectar.coneccion();
        String fechaLimite = txtFecha.getText();
        
        String pass=txtContraseña.getPassword().toString();
        String passEncriptado= DigestUtils.md5Hex(pass);
        int estado=0;
         sSQL = "select curdate() <= '"+fechaLimite+"'";
        
        try {
            PreparedStatement pst = con.prepareStatement(sSQL);
            ResultSet rs = pst.executeQuery(sSQL);
            while (rs.next()) {
                estado= rs.getInt(1);
            }
            JOptionPane.showMessageDialog(null, estado);

        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "introduzca la fecha con formato yyyy,mmm,dd ");
            //Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        sSQL = "insert into usuario(login, password, estado, fecha, nivel)"
                + " values(?,?,?,?,?) " ;
        try {
            PreparedStatement pst = con.prepareStatement(sSQL);
            //ResultSet rs = pst.executeQuery(sSQL);
            
            pst.setString(1, txtNombre.getText());
            pst.setString(2, passEncriptado);
            pst.setInt(3, estado);
            pst.setString(4, fechaLimite);
            pst.setString(5, txtNivel.getText());
            //pst.
            //pst.executeUpdate(sSQL);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario creado" );

        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error " + ex);
            //Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    
    public void actualizarUsuario() {
        int editado= tblUsuario.getEditingRow();
        //String strs= tblUsuario.is
        ConectarDB conectar = new ConectarDB();
        Connection con = conectar.coneccion();
        String fechaLimite = txtFecha.getText();
        int estado=0;
        String sSQL = "";
        
        sSQL = "select curdate() <= '"+fechaLimite+"'";
        
        try {
            PreparedStatement pst = con.prepareStatement(sSQL);
            ResultSet rs = pst.executeQuery(sSQL);
            while (rs.next()) {
                estado= rs.getInt(1);
            }
            //JOptionPane.showMessageDialog(null, estado);

        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "introduzca la fecha con formato yyyy,mmm,dd ");
            //Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        sSQL = "update usuario set login=?,password=?, estado=?, fecha=?, nivel=?"
                + " where login = 'usuario4' " ;
        try {
            PreparedStatement pst = con.prepareStatement(sSQL);
            //ResultSet rs = pst.executeQuery(sSQL);
            
            pst.setString(1, txtNombre.getText());
            pst.setString(2, DigestUtils.md5Hex(txtContraseña.getText()));
            pst.setInt(3, estado);
            pst.setString(4, fechaLimite);
            pst.setString(5, txtNivel.getText());
            
              pst.executeUpdate();
            //pst.ex
            //pst.executeUpdate(sSQL);
            JOptionPane.showMessageDialog(null, "Usuario " +txtNombre.getText()+" Actualizado" );
        
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error " + ex);
            //Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }
    public void eliminarUsuario(){
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalida = new javax.swing.JButton();
        txtNombre = new javax.swing.JTextField();
        txtContraseña = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnCrear = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtNivel = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuario = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnBitacora = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnBorrar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mPermiso = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSalida.setText("CerrarSesión");
        btnSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalidaActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 520, -1, -1));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 141, -1));
        getContentPane().add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 141, -1));

        jLabel1.setText("Nombre de Usuario");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));
        getContentPane().add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 141, -1));

        jLabel2.setText("Fecha Expiracion Acceso ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel3.setText("Contraseña");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        btnCrear.setText("Crear /Actualizar");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        getContentPane().add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 273, -1, 20));

        jLabel4.setText("Nivel de Acceso");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));
        getContentPane().add(txtNivel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 230, 37, -1));

        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblUsuario);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 313, 420, 201));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Creción"));
        jPanel1.setToolTipText("");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Invitado", "Editor", "Administrador" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(212, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 190, 310));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Busquedas"));
        jPanel2.setToolTipText("");

        btnBuscar.setText("Buscar usuario");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnBitacora.setText("Buscar Bitacora");
        btnBitacora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBitacoraActionPerformed(evt);
            }
        });

        jLabel5.setText("Parametros de busqueda");

        btnBorrar.setText("Limpiar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBitacora, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(6, 6, 6)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(btnBuscar)
                .addGap(18, 18, 18)
                .addComponent(btnBitacora)
                .addGap(117, 117, 117)
                .addComponent(btnBorrar)
                .addGap(31, 31, 31))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, -1, 310));

        mPermiso.setText("Permisos");
        mPermiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mPermisoActionPerformed(evt);
            }
        });
        jMenuBar1.add(mPermiso);

        jMenu1.setText("Ayuda");

        jMenuItem1.setText("jMenuItem1");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalidaActionPerformed
        // TODO add your handling code here:
        suiche = true;
        registarSalida();


    }//GEN-LAST:event_btnSalidaActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        borrar();
        int editado= tblUsuario.getEditingRow();
        int editad2= tblUsuario.getSelectedRow();
        String editado3="";
        if(stringSeleccion.equals("buscar")){
            editado3=(String) modelo.getValueAt(editad2, 0);
        }
        else if(stringSeleccion.equals("bitacora")){
            editado3=(String) serie.get(editad2);
        }
        
        
       // 
        
        JOptionPane.showMessageDialog(null, " "+editado+" "+editad2+" "+editado3);
        
        //ejecutarAccion();
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        MetodosMenu01  metodo =new MetodosMenu01(txtBuscar.getText(),modelo,tblUsuario);
        metodo.buscar();
        stringSeleccion= "buscar";
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnBitacoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBitacoraActionPerformed
        // TODO add your handling code here:
        MetodosMenu01 metodo= new MetodosMenu01(txtBuscar.getText(),modelo,tblUsuario,serie);
        metodo.verBitacora();
        //verBitacora();
        stringSeleccion="bitacora";
    }//GEN-LAST:event_btnBitacoraActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        
            // TODO add your handling code here:
            if(accionCrearActualizar.equals("crear")){
                crearUsuario();
            }
            else if(accionCrearActualizar.equals("actualizar")){
                actualizarUsuario();
            } 
            //crearUsuario();
           // 
           
        
    }//GEN-LAST:event_btnCrearActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void mPermisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mPermisoActionPerformed
        // TODO add your handling code here:
        //agregarAccionMenu(jMenuItem1);
    }//GEN-LAST:event_mPermisoActionPerformed

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
            java.util.logging.Logger.getLogger(Menu01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu01.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu01().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBitacora;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnSalida;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu mPermiso;
    private javax.swing.JTable tblUsuario;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNivel;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
