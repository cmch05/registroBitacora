/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProyect.mundo;

import java.sql.Connection;

/**
 *
 * @author cmch05
 */
public class ConectadoDB {
    //public ConectadoDB(){}
    private mainProyect.util.ConectarSql coneccion;
    public Connection login(){
        coneccion = new mainProyect.util.ConectarSql("root", "0920516", "localhost", "login");
        return coneccion.coneccion();
    }
    public Connection biblioteca(){
         coneccion = new mainProyect.util.ConectarSql("root", "0920516", "localhost", "biblioteca");
         return coneccion.coneccion();
    }
}
