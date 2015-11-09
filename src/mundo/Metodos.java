/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

import interfaces.Login;
import interfaces.Menu01;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author cmch05
 */
public class Metodos {
    
    private int perfil;
    private String sSQL;
    private PreparedStatement pst;
    private int errores=0;
    private ResultSet rs;
    private Connection con;
    //-------------------------
    private String usr,pass;

    
    //-------------------------
   public Metodos(ConectarDB conectar){
       this.conectar=conectar;
       
   }
    
    ConectarDB conectar =new ConectarDB();
    Login login = new Login();
    
    public void registroEntrada(){
        // boolean n=true;
        String  
                usr2="",pass2="";
                boolean estado= false;
        
     
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
            login.dispose();
            
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
    
}
