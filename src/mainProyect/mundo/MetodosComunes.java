
package mainProyect.mundo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import mainProyect.interfaces.Biblioteca;
import mainProyect.interfaces.Login;
import mainProyect.interfaces.Usuarios;
import mainProyect.util.ConectarSql;
import mainProyect.util.ConsultaSql;

/**
 *
 * @author cmch05
 */
public class MetodosComunes {
    private String interfaz, usuario;
//constructor para cambioInterfaz
    public MetodosComunes(String interfaz, String usuario, int perfil) {
        this.interfaz = interfaz;
        this.usuario = usuario;
        this.perfil = perfil;
    }
    private int perfil;
//constructor para registroSalida
    public MetodosComunes(String interfaz, int perfil) {
        this.interfaz = interfaz;
        this.perfil = perfil;
    }
    

    
    public MetodosComunes(){
    }
    private String sql;
    private final ConectarSql conectar= new ConectarSql("root","","localhost","biblioteca");
    private ConsultaSql consulta;
    
    
    
     public void registarSalida() {
         
        if(interfaz.equals("usuario")){
            sql = "call login.salidalogin()";
        consulta= new ConsultaSql(conectar.coneccion(), sql);
        consulta.getConsulta();
        }
        else if (interfaz.equals("biblioteca")) {
             sql = "call biblioteca.salidabiblioteca()";
        consulta= new ConsultaSql(conectar.coneccion(), sql);
        consulta.getConsulta();
        //Usuarios menu01 = new Usuarios(per);
       // menu01.setVisible(true);
         }
        else{
            JOptionPane.showMessageDialog(null, "Something has gone wrong");
        }
        
    }
     
     public void cambioInterfaz() {
         
         if(interfaz.equals("usuario")){
             Usuarios menu01 = new Usuarios(perfil);
            menu01.setVisible(true);
        // Login login = new Login();
        //b=true;

        sql= "call login.cambiointerface('"+usuario+"')";
        consulta= new  ConsultaSql(conectar.coneccion(), sql);
        consulta.getConsulta();
        //JOptionPane.showMessageDialog(null, usuario);
            
        }
        else if (interfaz.equals("biblioteca")) {
            Biblioteca biblioteca= new Biblioteca();
            biblioteca.setVisible(true);
            sql= "call biblioteca.registroentrada('"+usuario+"')";
            consulta= new  ConsultaSql(conectar.coneccion(), sql);
            consulta.getConsulta();
            //JOptionPane.showMessageDialog(null, usuario);
             
         }
        else{
            JOptionPane.showMessageDialog(null, "Something has gone wrong again ");
        }
         
        

    }
    
}
