/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundo;

import interfaces.Login;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author cmch05
 */
public class MetodosLogin {
    
    
     public void agregarAccionJTextFiel(JTextField m){
        m.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                char c=e.getKeyChar();
                
                if(c==KeyEvent.VK_ENTER){
                    // e.consume();
                   Login login = new Login();
                    login.registroEntrada();
                    //JOptionPane.showMessageDialog(null, "enter");
                }
            }
        });
    }
    
     
     public static void main(String[] args) {
        Login login = new Login();
        //login.setVisible(true);
        login.registroEntrada();
    }
}
