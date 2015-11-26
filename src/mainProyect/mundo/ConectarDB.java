/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProyect.mundo;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author cmch05
 */
public class ConectarDB {
    //private String usr,pass,server;

   
    public ConectarDB(){
        
    }
    
    //instanciamos Connection
    Connection conectar=null;

   
    //un metodo tipo Connection
    public Connection coneccion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //direccion base de datos, usurario, contraseña del usuario
            conectar=DriverManager.getConnection("jdbc:mysql://localhost/login","root","");
            
           // JOptionPane.showMessageDialog(null, "coneccion establecida");
        }
        //try catch validacion de errores
        catch(ClassNotFoundException | SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error en la coneccion "+e);
        }
        return conectar;
    }
    /*
     public Connection conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //direccion base de datos, usurario, contraseña del usuario
            conectar=DriverManager.getConnection("jdbc:mysql://"+server+"/login",usr,pass);
            
           // JOptionPane.showMessageDialog(null, "coneccion establecida");
        }
        //try catch validacion de errores
        catch(ClassNotFoundException | SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error: "+e);
        }
        return conectar;
    }
    */
}
