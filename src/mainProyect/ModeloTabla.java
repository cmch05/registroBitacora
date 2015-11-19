/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProyect;

import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cmch05
 */
public class ModeloTabla {
   // private JTable tbl;
    //private int largo;
    private DefaultTableModel modelo;
    private String sql;
    private String titulo[];
    private ConsultaSql consulta;

    private Connection con;
    public ModeloTabla(){
    }
    
    
    public ModeloTabla(String sql, String[] titulo, Connection coneccion) {
        //this.largo = largo;
        this.sql = sql;
        this.titulo = titulo;
        this.con=coneccion;
    }

    public  DefaultTableModel getModel(){
        String[] registro = new String[titulo.length];
        modelo = new DefaultTableModel(null, titulo);
        try {
            consulta= new ConsultaSql(con, sql);
            ResultSet rs= consulta.getResultSet();
            while(rs.next()){
                for (int i = 0; i < registro.length; i++) {
                    registro[i]= rs.getString(i+1);
                }
                modelo.addRow(registro);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " +e);
        }
       return modelo;
    }
    
}
