/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProyect.mundo;

import mainProyect.interfaces.Login;
import mainProyect.interfaces.Menu01;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cmch05
 */
public class Metodos {
    DefaultTableModel modelo;//= new DefaultTableModel();
    JTable tblUsuario;
    private int fila, columna;

    public Metodos(DefaultTableModel modelo,JTable tblUsuario, int fila, int columna) {
        this.modelo = modelo;
        this.tblUsuario=tblUsuario;
        this.fila=fila;
        this.columna=columna;
    }
    ConectarDB conectar =new ConectarDB();
    
    

   
    public void enterTabla(){
        modelo.addTableModelListener(new TableModelListener(){
             @Override
             public void tableChanged(TableModelEvent e){
                 //poner metod que se quiere accinar al editar campo de la tabla
                JOptionPane.showMessageDialog(null, "Editando");
               // comboBuscar();
             }
         });
    }
    public void comboBuscar() {

       /*
            int editadoFila = tblUsuario.getEditingRow();
        int editadoColumna =tblUsuario.getEditingColumn();
        int seleccionadoColumna = tblUsuario.getSelectedRow();
        int seleccionadoFila = tblUsuario.getSelectedColumn();
        String celdaSeleccionada = "";
        */
       
           String celdaSeleccionada = (String) tblUsuario.getModel().getValueAt(fila, columna);
      
            //celdaSeleccionada = (String) tblUsuario.getModel().getValueAt(editadoFila, 0);
        

        // 
        JOptionPane.showMessageDialog(null, " " + fila + " " + columna + " " + celdaSeleccionada);


        
    }
    
    
    
  
    
    
}
