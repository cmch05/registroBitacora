/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author cmch05
 */
public class Consulta {
    
    public ResultSet consulta(String sSQL){
        ResultSet rs = null;
    ConectarDB conectar = new ConectarDB();
        Connection con = conectar.coneccion();
        //int ser = 0;
        
        try {
            PreparedStatement pst = con.prepareStatement(sSQL);
            rs = pst.executeQuery(sSQL);
            while (rs.next()) {
               

            }
            pst.executeUpdate(sSQL);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error " + ex);
            //Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
