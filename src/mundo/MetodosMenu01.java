package mundo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cmch05
 */
public class MetodosMenu01 {
        private ConectarDB conectar ;
        private Connection con ;
        private PreparedStatement pst;
        private ResultSet rs ;
        private String sSQL;
    //-------------------------------------
    private String parametroBusqueda;
    private DefaultTableModel  modelo;
    private JTable tblUsuario;
    //------------------------------------------
    ArrayList serie;
    //-------------------------------------
    private int perfil;
    private JMenu mPermiso;
    //------------------------------
    //contructor para verBitacora
    public MetodosMenu01(String parametroBusqueda, DefaultTableModel modelo, JTable tblUsuario, ArrayList serie) {
        this.parametroBusqueda = parametroBusqueda;
        this.modelo = modelo;
        this.tblUsuario = tblUsuario;
        this.serie = serie;
    }
    //contructor para buscar
    public MetodosMenu01(String parametroBusqueda, DefaultTableModel modelo, JTable tblUsuario) {
        this.parametroBusqueda = parametroBusqueda;
        this.modelo = modelo;
        this.tblUsuario = tblUsuario;
    }
    
            
    public void buscar(){
        
        String titulo[]={"Nombre Usuario","Fecha Limite","Nivel Acceso"};
        String[] registro=new String[3];
        modelo= new DefaultTableModel(null,titulo);
        sSQL="select login, fecha, nivel from usuario"
                + " where concat(login,' ', fecha,' ',nivel)"
                + " like '%"+parametroBusqueda+"%'";
        
        conectar = new ConectarDB();
        con = conectar.coneccion();
        try {
            pst = con.prepareStatement(sSQL);
            rs = pst.executeQuery(sSQL);
            while (rs.next()) {
              registro[0]=rs.getString("login");
              registro[1]=rs.getString("fecha");
              registro[2]=rs.getString("nivel");
              
              modelo.addRow(registro);

            }
            tblUsuario.setModel(modelo);
            //pst.executeUpdate(sSQL);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error " + ex);
            //Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void verBitacora(){
        // un array un modelo una tabla un string
        String titulo[]={"Login","Fecha ingreso","Hora Ingreso", "Fecha salida", "Hora salida"};
        String[] registro=new String[5];
        modelo= new DefaultTableModel(null,titulo);
        
        
         sSQL="select serial, login, fecha_ingreso,hora_ingreso,fecha_salida, hora_salida from bitacora"
                    + " where concat(login,' ', fecha_ingreso,' ',fecha_salida,' ',hora_ingreso,' ', hora_salida)"
                    + " like '%"+parametroBusqueda+"%'";
        
         conectar = new ConectarDB();
         con = conectar.coneccion();
        try {
             pst = con.prepareStatement(sSQL);
             rs = pst.executeQuery(sSQL);
            while (rs.next()) {
              registro[0]=rs.getString("login");
              registro[1]=rs.getString("fecha_ingreso");
              registro[2]=rs.getString("hora_ingreso");
              registro[3]=rs.getString("fecha_salida");
              registro[4]=rs.getString("hora_salida");
              modelo.addRow(registro);
              serie.add(rs.getString("serial"));

            }
            tblUsuario.setModel(modelo);
            //pst.executeUpdate(sSQL);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error " + ex);
            //Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarJMenuItem() {
        //un menu un int
         conectar = new ConectarDB();
        String contador = "";
         con = conectar.coneccion();
        
        sSQL = "select distinct permiso from perfil_permiso left join permiso on "
                + "perfil_permiso.id_perfil='"+perfil+"'and  perfil_permiso.id_permiso=permiso.id";
        
        
        //select permiso from perfil_permiso left join permiso on perfil_permiso.id_perfil='2'and  perfil_permiso.id_permiso=permiso.id;
        try {
            PreparedStatement pst = con.prepareStatement(sSQL);
            ResultSet rs = pst.executeQuery(sSQL);
            int i=0;
            while (rs.next()) {
                if(rs.getString("permiso")!=null){
                
                mPermiso.add(rs.getString("permiso")+" usuario");
                   agregarAccionMenu(mPermiso.getItem(i));
                   nombreMenuItem.add(mPermiso.getItem(i).getText());
               // cboTablas.addItem(rs.getString("id_perfil"));
               
               //JOptionPane.showMessageDialog(null, mPermiso.getItem(i).getText());
               i++;
                }
            }
            
              //JOptionPane.showMessageDialog(null, "exito ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error " + ex);
            //Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
