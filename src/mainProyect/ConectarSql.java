/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProyect;

import java.awt.HeadlessException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author cmch05
 */
public class ConectarSql {
    private String usr,pass,server,dataBase;
    private Connection con=null;
    
    public ConectarSql(String usr, String pass, String server,String dataBase) {
        this.usr = usr;
        this.pass = pass;
        this.server = server;
        this.dataBase=dataBase;
    }
    
     public Connection coneccion(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://"+server+"/"+dataBase,usr,pass);
        }
        
        catch(ClassNotFoundException | SQLException | HeadlessException ex){
            JOptionPane.showMessageDialog(null, "No se puede establecer coneccion con el servidor");
            Logger.getLogger(ConectarSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
}
