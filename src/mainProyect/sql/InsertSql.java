/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProyect.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author cmch05
 */
public class InsertSql {
    private Connection con;
    private String sql;

    public InsertSql(String sql, String[] campos,Connection con) {
        this.con = con;
        this.sql = sql;
        this.campos = campos;
    }
    private PreparedStatement pst;
    private String[] campos;
    
    public InsertSql(){
    }
 
    public void getInsert(){
        
        try {
            pst = con.prepareStatement(sql);
            for (int i = 0; i < campos.length; i++) {

                    pst.setString(i+1, campos[i]);
                    
            }
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "informacion insertada correctamente");
        } 
        catch (SQLException ex) {
                Logger.getLogger(InsertSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
