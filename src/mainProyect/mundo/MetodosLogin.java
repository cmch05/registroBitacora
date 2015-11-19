/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProyect.mundo;

import mainProyect.interfaces.*;

import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import mainProyect.*;



/**
 *
 * @author cmch05
 */
public class MetodosLogin {
    private JTextField txtContraseña, txtUsuario;

    
    private  String pass, usr,sSQL;
    private PreparedStatement pst;
    private Connection con;
    private final ConectarSql conectar = new ConectarSql("root","","localhost","login");
    private int perfil;
    private static int errores;
    private ResultSet rs;
    //private ConsultaSql consulta =new ConsultaSql(conectar.coneccion(),sSQL);
    private boolean b=false;

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public MetodosLogin() {
        
    }
    public MetodosLogin(JTextField txtContraseña) {
        this.txtContraseña = txtContraseña;
        agregarAccionJTextFiel(txtContraseña);
        
    }
    public MetodosLogin(String pass, String usr) {
        this.pass = pass;
        this.usr = usr;
    }
    
     public void agregarAccionJTextFiel(JTextField m){
        m.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                char c=e.getKeyChar();
                
                if(c==KeyEvent.VK_ENTER){
                    
                    registroEntrada();
                }
            }
        });
    }
    
     
    public void registroEntrada(){
       
        String  
                usr2="",pass2="";
                boolean estado= false;
        sSQL="select login,password,estado,fecha, nivel from usuario"
                + " where login = '"+usr+"' and fecha >= curdate() and "
                + "password= md5('"+pass+"') and estado = true";
        // JOptionPane.showMessageDialog(null, sSQL);
        
        
        try {
            ConsultaSql consulta =new ConsultaSql(conectar.coneccion(),sSQL);
            rs= consulta.getResultSet();
            //pst= con.prepareStatement(sSQL);
            //rs= pst.executeQuery(sSQL);
            while (rs.next()) {
                usr2=rs.getString("login");
                pass2= rs.getString("password");
                estado=rs.getBoolean("estado");
                perfil=rs.getInt("nivel");
            }
           
           // pst.execute();
             
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " error "+ex);
            //*Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (usr.equals(usr2)&& estado==true){ //&&(fecha==fechaActual ||fecha.after(fechaActual))){
            if(perfil==1){
                b=true;
                JOptionPane.showMessageDialog(null, "Bienvenido Administrador");
                cambioInterfaz(perfil,usr);
                
            }
            else if(perfil==2){
                b=true;
                JOptionPane.showMessageDialog(null, "Bienvenido Visitante");
                cambioInterfaz(perfil,usr);
            }
            else if(perfil==3){
                b=true;
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
        Usuarios menu01=new Usuarios(per);
        menu01.setVisible(true);
       // Login login = new Login();
        //b=true;
        
        //login.dispose();
        
            sSQL="insert into bitacora(login,fecha_ingreso,hora_ingreso)"
                    + "values('"+usr+"',curdate(),curtime())";
              //  + " where login = '"+usr+"' and fecha >= current_date() and";
            //like
            
            try {
                // JOptionPane.showMessageDialog(null, "veeeeeer ");
              con = conectar.coneccion();  
             pst= con.prepareStatement(sSQL);
            pst.execute();
            } 
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "segundo error "+ex);
                //*Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
    } 
     
     
     
     
     
     public static void main(String[] args) {
      //  Login login = new Login();
        //login.setVisible(true);
       // login.registroEntrada();
       //Login login = new Login();
    }
}
