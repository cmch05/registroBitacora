
package mainProyect.sql;

public class SecuenciasConsultaSql {
    private String parametro;
    
    public SecuenciasConsultaSql(){
    }
    public SecuenciasConsultaSql(String parametro) {
        this.parametro = parametro;
    }
    
    private String sql;
    private String[]titulo;
    private String  nombre,
                    apellido,
                    cedula,
                    direccion,
                    nacimiento,
                    ciudad,
                    departamento;

    public SecuenciasConsultaSql(String nombre, String apellido, String cedula, 
            String direccion, String nacimiento, String ciudad, String departamento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.direccion = direccion;
        this.nacimiento = nacimiento;
        this.ciudad = ciudad;
        this.departamento = departamento;
    }
    //----------------------------------------------------------
    public String buscarUsuario(){
        sql="call biblioteca.buscarusuario('"+parametro+"')"  ;  
    return sql;
    }
    //--------------------------------------------------------
    public String[] tituloBuscarUsuario(){
         titulo=new String[]{"Codigo","Nombre","Apellido","Cedula","Direccion","Ciudad",
            "Departamento","Fecha de Nacimiento"};
        return titulo;
    }
    //********************************************
    public String buscarLibro(){
        sql="call biblioteca.buscarlibro('"+parametro+"')"  ; 
    return sql;
    }
    public String[] tituloBuscarlibro(){
        String titulo[]={"Codigo","Nombre","Editorial","Autor","Genero","Pais del Autor",
            "Paginas","Fecha de Edicion","Precio"};
        return titulo;
    }
    public String nuevoLibro(){
        
        // pendiente de organizacion del procedimineto
        sql="call biblioteca.nuevolibro"
                + "('progamacion con java', 'Thomson Learning', 'Rick Decker ,"
                + " Stuar Hirshfield', 'Educacion', 'USA', 618, '2001', 2900)";
        
        
        sql="insert into libro(nombre, editorial, autor, genero, pais_autor,"
            + "paginas, anno_edicion, precio) values(?,?,?,?,?,?,?,?)";
        
        return sql;
    }
    
    public String nuevoUsuario(){
        
        // pendiente de organizacion del procedimineto
        sql="call biblioteca.nuevousuario('"+nombre+"','"+apellido+"', '"+cedula+"',"
                + " '"+direccion+"', '"+ciudad+"', '"+departamento+"', '"+nacimiento+"')";
       
        
        return sql;
    }
    //--------------------------------------------------------------
    public String selectDepartamento(){
        sql="call biblioteca.selectdepartamento()";
        return sql;
    }
    public String selectCiudad(){
        sql="call biblioteca.selectciudad('"+parametro+"');";
        return sql;
    }
    //------------------------------------------------------------------
    
}
