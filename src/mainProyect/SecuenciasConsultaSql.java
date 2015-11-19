/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProyect;

/**
 *
 * @author cmch05
 */
public class SecuenciasConsultaSql {
    private String parametro;
    
    public SecuenciasConsultaSql(){
    }
    public SecuenciasConsultaSql(String parametro) {
        this.parametro = parametro;
    }
    private String sql;
    
    
    public String buscarUsuario(){
    sql = "select codigo, nombre, apellido,cedula,domicilio, ciudad,"
            + "departamento,nacimiento from usuario"
                + " where concat(nombre,' ', apellido,' ',cedula,' ',domicilio,"
            + "' ', ciudad,' ', departamento,' ', nacimiento)"
                + " like '%" + parametro + "%'";
    return sql;
    }
    public String[] tituloBuscarUsuario(){
        String titulo[]={"Codigo","Nombre","Apellido","Cedula","Direccion","Ciudad",
            "Departamento","Fecha de Nacimiento"};
        return titulo;
    }
    //********************************************
    public String buscarLibro(){
    sql = "select codigo, nombre, editorial,autor,genero, pais_autor,"
            + "paginas,anno_edicion,precio from libro"
                + " where concat(codigo,' ',nombre,' ', editorial,' ',autor,' ',genero,"
            + "' ', pais_autor,' ', paginas,' ', anno_edicion,' ',precio)"
                + " like '%" + parametro + "%'";
    return sql;
    }
    public String[] tituloBuscarlibro(){
        String titulo[]={"Codigo","Nombre","Editorial","Autor","Genero","Pais del Autor",
            "Paginas","Fecha de Edicion","Precio"};
        return titulo;
    }
}
