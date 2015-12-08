package mainProyect.mundo;

import mainProyect.util.ConsultaSql;
import mainProyect.util.ConectarSql;
import mainProyect.interfaces.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class MetodosLogin {
    private JButton btnBiblioteca;
    private JButton btnUsuario;
    private boolean habilitadoBiblioteca= false;
    private boolean habilitadoUsuario= false;
//****************reestructura***************************
    public boolean isHabilitadoUsuario() {
        return habilitadoUsuario;
    }
    public boolean isHabilitadoBiblioteca() {
        return habilitadoBiblioteca;
    }
    public int getPerfil() {
        return perfil;
    }
    public String getUsr() {
        return usr;
    }
    //*******************************
    private JTextField txtContraseña, txtUsuario;
    private ConsultaSql consulta;
    private Connection con;
    private final ConectarSql conectar = new ConectarSql("root", "", "localhost", "login");

    private String pass, usr, sSQL;

    
    private PreparedStatement pst;
    private int perfil;

    
    private static int errores;
    private ResultSet rs;
    //private ConsultaSql consulta =new ConsultaSql(conectar.coneccion(),sSQL);
    private boolean b = false;

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public MetodosLogin() {

    }

    public MetodosLogin(JTextField txtContraseña) {
        this.txtContraseña = txtContraseña;
        agregarAccionJTextFiel(txtContraseña);

    }

    public MetodosLogin(String pass, String usr) {
        this.pass = pass;
        this.usr = usr;
    }

    public void agregarAccionJTextFiel(JTextField m) {
        m.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if (c == KeyEvent.VK_ENTER) {

                    registroEntrada();
                }
            }
        });
    }

    public void registroEntrada() {

        String respuesta = "";

        sSQL = "call login.login('" + usr + "', '" + pass + "')";

        try {
            consulta = new ConsultaSql(conectar.coneccion(), sSQL);
            rs = consulta.getResultSet();

            while (rs.next()) {
                respuesta = rs.getString(1);
                perfil = rs.getInt(2);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, " error " + ex);
            //*Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (respuesta.equals("usuario_aceptado")) {
            if (perfil == 1) {
                //b = true;
                JOptionPane.showMessageDialog(null, "Bienvenido Administrador");
                this.habilitadoBiblioteca=true;
                this.habilitadoUsuario=true;
                
                //cambioInterfaz(perfil, usr);
            } else if (perfil == 2) {
                this.habilitadoBiblioteca=true;
                this.habilitadoUsuario=false;
                //b = true;
                JOptionPane.showMessageDialog(null, "Bienvenido Bibliotecario");
                //cambioInterfaz(perfil, usr);
            } else if (perfil == 3) {
                //b = true;
                this.habilitadoBiblioteca=true;
                this.habilitadoUsuario=true;
                JOptionPane.showMessageDialog(null, "Bienvenido Editor");
                //cambioInterfaz(perfil, usr);
            }
        } else {
            if (errores < 2) {
                if (respuesta.equals("usuario_incorrecto")) {
                    JOptionPane.showMessageDialog(null, "Usuario Incorrecto");
                } 
                else if (respuesta.equals("contrasaña_incorrecta")) {
                    JOptionPane.showMessageDialog(null, "Contrasaña Incorrecta");
                } 
                else if (respuesta.equals("sin_permiso")) {
                    JOptionPane.showMessageDialog(null, "Sin Permisos, comuniquese "
                            + "con un Administrador");
                }
                else if(respuesta.equals("Fecha_caduca")){
                    JOptionPane.showMessageDialog(null, "Fecha Caducada, comuniquese "
                            + "con un Administrador");
                }
            } 
            else {
                JOptionPane.showMessageDialog(null, "Has superado el limite de intentos, "
                                                     + "prueba mas tarde");
                System.exit(0);
            }
            errores++;
        }
    }

    public void cambioInterfaz(int per, String usr) {
        Usuarios menu01 = new Usuarios(per);
        menu01.setVisible(true);
        // Login login = new Login();
        //b=true;

        sSQL= "call login.cambiointerface('"+usr+"')";
        consulta= new  ConsultaSql(conectar.coneccion(), sSQL);
        consulta.getConsulta();
        // JOptionPane.showMessageDialog(null, usr);

    }

    public static void main(String[] args) {
        //  Login login = new Login();
        //login.setVisible(true);
        // login.registroEntrada();
        //Login login = new Login();
    }
}
