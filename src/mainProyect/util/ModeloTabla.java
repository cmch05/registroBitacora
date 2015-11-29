/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProyect.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cmch05
 */
public class ModeloTabla {

    private DefaultTableModel modelo;
    private String sql;
    private String titulo[];
    private Connection con;

    public ModeloTabla() {
    }

    public ModeloTabla(String sql, String[] titulo, Connection coneccion) {
        //this.largo = largo;
        this.sql = sql;
        this.titulo = titulo;
        this.con = coneccion;
    }

    public ModeloTabla(String sql, Connection coneccion) {
        //this.largo = largo;
        this.sql = sql;
        //this.titulo = titulo;
        this.con = coneccion;
    }

    public DefaultTableModel getModel() {
        String[] registro = new String[titulo.length];
        modelo = new DefaultTableModel(null, titulo);
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            while (rs.next()) {
                for (int i = 0; i < registro.length; i++) {
                    registro[i] = rs.getString(i + 1);
                }
                modelo.addRow(registro);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return modelo;
    }

    public DefaultTableModel getModel2() {
        try {

            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();

            //String nombre="";
            int largo = rsmd.getColumnCount();
            String[] registro = new String[largo];
            String[] titulo2 = new String[largo];

            for (int i = 0; i < largo; i++) {
                titulo2[i] = rsmd.getColumnLabel(i + 1);
            }
            modelo = new DefaultTableModel(null, titulo2);
            while (rs.next()) {

                for (int i = 0; i < largo; i++) {
                    registro[i] = rs.getString(i + 1);
                }
                modelo.addRow(registro);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        return modelo;
    }

}
