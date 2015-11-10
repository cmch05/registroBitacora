package mundo;

import interfaces.Menu01;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cmch05
 */
public class MetodosMenu01 {
    public MetodosMenu01(){
}
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
    private JTextField txtNombre,txtFecha,txtContraseña,txtNivel,txtBuscar;
    private JButton btnBuscar,btnBitacora,btnCrear;
    private String accionCrearActualizar;
    //---------------------------------
    //constructor para agregarAccionMenu
    public MetodosMenu01(JTextField txtNombre, JTextField txtFecha, JTextField txtContraseña, 
            JTextField txtNivel, JTextField txtBuscar, JButton btnBuscar, JButton btnBitacora, JButton btnCrear) {
        this.txtNombre = txtNombre;
        this.txtFecha = txtFecha;
        this.txtContraseña = txtContraseña;
        this.txtNivel = txtNivel;
        this.txtBuscar = txtBuscar;
        this.btnBuscar = btnBuscar;
        this.btnBitacora = btnBitacora;
        this.btnCrear = btnCrear;
    }
    
    //contructor para cargarJMenuItem
    public MetodosMenu01(int perfil, JMenu mPermiso) {
        this.perfil = perfil;
        this.mPermiso = mPermiso;
    }
    
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
    
    
    
    
    
    /*
    public void cargarJMenuItem() {
        Menu01 menu01=new Menu01();
    
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
                   //nombreMenuItem.add(mPermiso.getItem(i).getText());
               
               i++;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error " + ex);
        }
    }
    
    
    public void agregarAccionMenu(JMenuItem m){
        
        m.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                
                if(m.getText().equals("ver usuario")){
                    
                    accionVerEliminar();
                        
                }
                else if(m.getText().equals("crear usuario")){
                    
                        accionCrearActualizar();
                        accionCrearActualizar="crear";
                         Menu01 menu01=new Menu01();
                        menu01.setAccionCrearActualizar(accionCrearActualizar);
                    
                }
                else if(m.getText().equals("actualizar usuario")){
                    
                    
                        accionCrearActualizar();
                        accionCrearActualizar="actualizar";
                        Menu01 menu01=new Menu01();
                        menu01.setAccionCrearActualizar(accionCrearActualizar);
                }
            }
        });
    }
    public void accionCrearActualizar(){
                        txtContraseña.setEnabled(true);
                        txtFecha.setEnabled(true);
                        txtNivel.setEnabled(true);
                        txtNombre.setEnabled(true);
                        btnCrear.setEnabled(true);
                        txtBuscar.setEnabled(true);
                        btnBitacora.setEnabled(true);
                        btnBuscar.setEnabled(true);
        
    }
    public void accionVerEliminar(){
                        txtContraseña.setEnabled(false);
                        txtFecha.setEnabled(false);
                        txtNivel.setEnabled(false);
                        txtNombre.setEnabled(false);
                        btnCrear.setEnabled(false);
                        txtBuscar.setEnabled(true);
                        btnBitacora.setEnabled(true);
                        btnBuscar.setEnabled(true);
    }
    */
    
   public void agregarAccionBuscar(JTextField m){
        m.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                char c=e.getKeyChar();
                
                    buscar();
                     if(c==KeyEvent.VK_BACK_SPACE){
                   
                    MetodosMenu01  metodo =new MetodosMenu01(txtBuscar.getText(),modelo,tblUsuario);
                   //tblUsuario.setModel(new DefaultTableModel());
                    metodo.buscar();
                }
                
            }
        });
    }
    

}
