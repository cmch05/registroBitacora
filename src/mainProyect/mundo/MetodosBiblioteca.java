/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProyect.mundo;

import mainProyect.interfaces.Biblioteca;

/**
 *
 * @author cmch05
 */
public class MetodosBiblioteca {
    private String sql;
    
    //public void cambioInterfaz(int per, String usr) {
    public void cambioInterfaz() {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.setVisible(true);
        // Login login = new Login();
        //b=true;

        //sql= "login.cambiointerface("+usr+")";
        //consulta= new  ConsultaSql(conectar.coneccion(), sSQL);

    }
    
}
