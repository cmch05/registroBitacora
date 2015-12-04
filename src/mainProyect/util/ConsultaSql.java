/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProyect.util;
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
        //con.close();
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
        //con.close();
        }
        catch(SQLException ex){
            //borrar este JOptionPane depsues de verificar todo
            JOptionPane.showMessageDialog(null, "Nº error "+ex.getErrorCode()+" error "+ex);
                                    //ex.getErrorCode()); codigo para tomar el numero de error
            if (ex.getErrorCode()==1265) {
                JOptionPane.showMessageDialog(null, "Verifique los valores numericos ingresados, antes de enviar");
            }
            if (ex.getErrorCode()==0) {
                JOptionPane.showMessageDialog(null, "Verifique que el Formato de fecha sea YYYY-MM-DD antes de enviar");
            }
        }
        
    }
    
}
