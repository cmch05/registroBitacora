/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProyect.sql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author cmch05
 */
public class ConsultaSql {
    private ResultSet rs;
    private Connection con;
    private PreparedStatement pst;
    private String sql;
    public ConsultaSql(Connection con, String sql) {
        this.con = con;
        this.sql = sql;
    }
    public ResultSet getResultSet(){
        try{
        pst=con.prepareStatement(sql);
        rs=pst.executeQuery(sql);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, " error "+ex);
        }
        return rs;
    }
    public void getConsulta(){
        try{
        pst=con.prepareStatement(sql);
        pst.executeQuery(sql);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, " error "+ex);
        }
    }
    
}
