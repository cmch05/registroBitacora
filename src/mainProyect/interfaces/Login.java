
package mainProyect.interfaces;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import mainProyect.mundo.MetodosLogin;

/**
 *
 * @author cmch05
 */
public class Login extends javax.swing.JFrame {
    /*
    private int perfil;
    private String sSQL;
    private PreparedStatement pst;
    private int errores=0;
    private ResultSet rs;
    private Connection con;
    */
    public Login() {
        initComponents();
        //MetodosLogin metodo = new MetodosLogin(txtContraseña);
        agregarAccionJTextFiel(txtContraseña);
        this.setResizable(false);
        this.setLocation(580,250);
    }
   // ConectarDB conectar =new ConectarDB();
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        txtUsuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("BD Acces");
        setForeground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Acceso a los Usuarios"));

        jButton1.setText("Entrar");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Usuario");

        jLabel2.setText("Contraseña");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(129, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(6, 6, 6)
                .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleDescription("BD Acces Cristian");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       llamada();
    }//GEN-LAST:event_jButton1ActionPerformed
    public void agregarAccionJTextFiel(JTextField m){
        m.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                char c=e.getKeyChar();
                
                if(c==KeyEvent.VK_ENTER){
                    llamada();
                }
            }
        });
    }
    //boolean b=false;
    public void llamada(){
        
        String pass= txtContraseña.getText();
        String usr = txtUsuario.getText();
        MetodosLogin metodo=new MetodosLogin( pass, usr);
       
        metodo.registroEntrada();
        
        
       // this.dispose();
        
       // Menu01 menu01=new Menu01();
        if(metodo.isB()){
            //System.exit(0);
            this.dispose();
        }
        
    }
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

    /*
    
    
    */
    /*
    public void registroEntrada(){
        // boolean n=true;
        String usr="" , pass="", 
                usr2="",pass2="";
                boolean estado= false;
        usr = txtUsuario.getText();
        pass =txtContraseña.getText();
     
        sSQL="select login,password,estado,fecha, nivel from usuario"
                + " where login = '"+usr+"' and fecha >= curdate() and "
                + "password= md5('"+pass+"') and estado = true";
        // JOptionPane.showMessageDialog(null, sSQL);
        con = conectar.coneccion();
        
        try {
            pst= con.prepareStatement(sSQL);
            rs= pst.executeQuery(sSQL);
            while (rs.next()) {
                usr2=rs.getString("login");
                pass2= rs.getString("password");
                estado=rs.getBoolean("estado");
                perfil=rs.getInt("nivel");
            }
           
            pst.execute();
             
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " error "+ex);
            //*Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (usr.equals(usr2)&& estado==true){ //&&(fecha==fechaActual ||fecha.after(fechaActual))){
            if(perfil==1){
                JOptionPane.showMessageDialog(null, "Bienvenido Administrador");
                cambioInterfaz(perfil,usr);
                
            }
            else if(perfil==2){
                JOptionPane.showMessageDialog(null, "Bienvenido Visitante");
                cambioInterfaz(perfil,usr);
            }
            else if(perfil==3){
                JOptionPane.showMessageDialog(null, "Bienvenido Editor");
                cambioInterfaz(perfil,usr);
            }
        }
        else{
            if(errores<2){
            
            JOptionPane.showMessageDialog(null, "usuario incorrecto");
            }
            else{
                JOptionPane.showMessageDialog(null, "Has superado el limite de intentos, prueba mas tarde");
            System.exit(0);
            }
            errores++;
        }
        
    }
    public void cambioInterfaz(int per, String usr){
        Menu01 menu01=new Menu01(per);
        menu01.setVisible(true);
            this.dispose();
            
            sSQL="insert into bitacora(login,fecha_ingreso,hora_ingreso)"
                    + "values('"+usr+"',curdate(),curtime())";
              //  + " where login = '"+usr+"' and fecha >= current_date() and";
            //like
            
            try {
             pst= con.prepareStatement(sSQL);
            pst.execute();
            } 
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "segundo error "+ex);
                //*Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
   */
    
}

