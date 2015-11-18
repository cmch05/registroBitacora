/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProyect;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author cmch05
 */
public class ConectarSql {
    private String usr,pass,server,dataBase;

    public ConectarSql(String usr, String pass, String server,String dataBase) {
        this.usr = usr;
        this.pass = pass;
        this.server = server;
        this.dataBase=dataBase;
    }
    //instanciamos Connection
    Connection con=null;
    //un metodo tipo Connection
    
     public Connection coneccion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //direccion base de datos, usurario, contraseña del usuario
            con=DriverManager.getConnection("jdbc:mysql://"+server+"/"+dataBase,usr,pass);
            
           // JOptionPane.showMessageDialog(null, "coneccion establecida");
        }
        //try catch validacion de errores
        catch(ClassNotFoundException | SQLException | HeadlessException e){
            JOptionPane.showMessageDialog(null, "Error: "+e);
        }
        return con;
    }
    
}
