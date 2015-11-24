/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProyect.sql;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author cmch05
 */
public class UpdateSql {
    
    //private ResultSet rs;
    private Connection con;
    private PreparedStatement pst;
    private String sql;
    private int inicio,fin;
    private ArrayList lista;
    //getUpdateSerie
    public UpdateSql(Connection con, String sql,ArrayList lista) {
        this.con = con;
        this.sql = sql;
        this.lista=lista;
    }
    //getUpdateNoSerie
    public UpdateSql(Connection con, String sql,ArrayList lista,int inicio, int fin) {
        this.con = con;
        this.sql = sql;
        this.lista=lista;
        this.inicio= inicio;
        this.fin= fin;
    }
    
    
    
    public void getUpdateNoSerie(){
        try{
        pst=con.prepareStatement(sql);
        //rs=pst.executeQuery(sql);
        int j=0;
            for (int i = inicio; i <= fin; i++) {
                pst.setString(i, lista.get(j-1).toString());
                j++;
            }
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, " error "+ex);
        }
    }
    public void getUpdateSerie(){
        try{
            pst=con.prepareStatement(sql);

            pst.setArray(inicio, (Array) lista);
        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, " error "+ex);
        }
    }
}
