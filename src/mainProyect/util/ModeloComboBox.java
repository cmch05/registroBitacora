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
import java.util.logging.*;
import javax.swing.*;

/**
 *
 * @author cmch05
 */
public class ModeloComboBox {

    private final DefaultComboBoxModel modelo = new DefaultComboBoxModel();
    private final String sql;
    private final Connection con;

    public ModeloComboBox(String sql, Connection con) {
        this.sql = sql;
        this.con = con;
    }

    public DefaultComboBoxModel getModel(int posicion) {
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            //modelo.removeAllElements();

            while (rs.next()) {
                modelo.addElement(rs.getString(posicion));
            }
        } 
        catch (SQLException | NullPointerException ex) {
            //JOptionPane.showMessageDialog(null, "Nose puede lleanar el ComboBox");
            Logger.getLogger(ModeloComboBox.class.getName()).log(Level.SEVERE, null, ex);
        }
        //JOptionPane.showMessageDialog(null, "Nose puede lleanar el ComboBox");
        // System.exit(0);
        return modelo;
    }
}
