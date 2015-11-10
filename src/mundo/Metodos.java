/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

import interfaces.Login;
import interfaces.Menu01;
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

    public Metodos(DefaultTableModel modelo) {
        this.modelo = modelo;
    }
    ConectarDB conectar =new ConectarDB();
    
    

   
    public void enterTabla(){
        modelo.addTableModelListener(new TableModelListener(){
             @Override
             public void tableChanged(TableModelEvent e){
                 //poner metod que se quiere accinar al editar campo de la tabla
                JOptionPane.showMessageDialog(null, "Editando");
             }
         });
    }
    
    
  
    
    
}
