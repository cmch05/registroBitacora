
package mainProyect.interfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import mainProyect.mundo.ConectarDB;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import mainProyect.mundo.MetodosUsuarios;
import org.apache.commons.codec.digest.DigestUtils;// modulo common

/**
 *
 * @author cmch05
 */
public class Usuarios extends javax.swing.JFrame {

    private int perfil, nivel;
    private DefaultTableModel modelo;
    private ArrayList serie = new ArrayList();
    private String stringSeleccion, sSQL;
    private String accionCrearActualizar;
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;
    private String itemComboBox;
    private ArrayList niveles = new ArrayList();
    private int editadoFila, editadoColumna ,serieSeleccionada, seleccionadoColumna,seleccionadoFila;
    String usuarioSeleccionado;   

    
   
    
    public void setAccionCrearActualizar(String accionCrearActualizar) {
        this.accionCrearActualizar = accionCrearActualizar;
    }

    public Usuarios(int perfil) {
        Login login = new Login();
        login.setVisible(false);
        
        this.setLocation(480,100);
        //this.setResizable(false);
        this.perfil = perfil;
        initComponents();

        cargarJMenuItem();
        seleccioNivel();

        //MetodosMenu01 metodo = new MetodosUsuarios();
        //metodo.agregarAccionBuscar(txtBuscar);
        agregarAccionBuscar(txtBuscar);
        antesCerrar();
        desHabilitar();
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }
    boolean suiche = false;

    public Usuarios() {
        this.setLocation(480,100);
        //setResizable(false);
        initComponents();
        seleccioNivel();
        cboBuscar.setEditable(true);
        
    }

    // previene la aslida y despues escucha el boton de salida y le dice que haga algo
    public void antesCerrar() {
        setDefaultCloseOperation(Usuarios.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                registarSalida();
            }

        });

        // setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }

    public void registarSalida() {
        ConectarDB conectar = new ConectarDB();
        con = conectar.coneccion();
        int ser = 0;
         sSQL = "";
        try {
            sSQL = "update bitacora set fecha_salida = curdate() , hora_salida = curtime() order by serial desc limit 1 ";
             //       + "where serial ='" + ser + "' ";
            //pst= con.prepareStatement(sSQL);
           PreparedStatement pst = con.prepareStatement(sSQL);
          // ResultSet rs = pst.executeQuery(sSQL);
            pst.executeUpdate(sSQL);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error " + ex);
        }
        //this.dispose();
        if (suiche == true) {
            this.dispose();
            Login login = new Login();
            login.setVisible(suiche);
        } else {

            System.exit(0);
        }
    }

    public void cargarJMenuItem() {
        //JOptionPane.showMessageDialog(null, perfil);
        ConectarDB coneccion = new ConectarDB();
        String contador = "";
        con = coneccion.coneccion();

        sSQL = "select permiso from permiso join perfil_permiso on "
                + " permiso.id=perfil_permiso.id_permiso where "
                + "perfil_permiso.id_perfil='"+perfil+"'";

        try {
            PreparedStatement pst = con.prepareStatement(sSQL);
            ResultSet rs = pst.executeQuery(sSQL);
            int i = 0;
            while (rs.next()) {
                if (rs.getString("permiso") != null) {

                    mPermiso.add(rs.getString("permiso") + " usuario");
                    agregarAccionMenu(mPermiso.getItem(i));
                    //nombreMenuItem.add(mPermiso.getItem(i).getText());
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

    public void agregarAccionMenu(JMenuItem m) {

        m.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {

                if (m.getText().equals("ver usuario")) {
                    txtContraseña.setEnabled(false);
                    txtFecha.setEnabled(false);
                    cboNivel.setEnabled(false);
                    txtNombre.setEnabled(false);
                    btnCrear.setEnabled(false);
                    txtBuscar.setEnabled(true);
                    //btnBitacora.setEnabled(true);
                    //btnBuscar.setEnabled(true);
                    cboBuscar.setEnabled(true);
                    

                } else if (m.getText().equals("crear usuario")) {

                    txtContraseña.setEnabled(true);
                    txtFecha.setEnabled(true);
                    cboNivel.setEnabled(true);
                    txtNombre.setEnabled(true);
                    btnCrear.setEnabled(true);
                    txtBuscar.setEnabled(true);
                    //btnBitacora.setEnabled(true);
                    //btnBuscar.setEnabled(true);
                    cboBuscar.setEnabled(true);
                    accionCrearActualizar = "crear";
                    pn1.setBorder(javax.swing.BorderFactory.createTitledBorder("Crear Usuario"));

                } else if (m.getText().equals("actualizar usuario")) {

                    txtContraseña.setEnabled(true);
                    txtFecha.setEnabled(true);
                    cboNivel.setEnabled(true);
                    txtNombre.setEnabled(true);
                    btnCrear.setEnabled(true);
                    txtBuscar.setEnabled(true);
                    //btnBitacora.setEnabled(true);
                    //btnBuscar.setEnabled(true);
                    cboBuscar.setEnabled(true);
                    accionCrearActualizar = "actualizar";
                    pn1.setBorder(javax.swing.BorderFactory.createTitledBorder("Actualizar Usuario"));
                }
            }
        });
    }

    public void ejecutarAccion() {
        String Mselct = (String) mPermiso.getItem(mPermiso.getWidth()).getText();
        JOptionPane.showMessageDialog(null, Mselct);
    }

    public void desHabilitar() {
        // txtBuscar.setEnabled(false);
        txtContraseña.setEnabled(false);
        txtFecha.setEnabled(false);
        cboNivel.setEnabled(false);
        txtNombre.setEnabled(false);
        btnCrear.setEnabled(false);
        txtBuscar.setEnabled(false);
        //btnBitacora.setEnabled(false);
        //btnBuscar.setEnabled(false);
        cboBuscar.setEnabled(false);

    }

    public void habilitarActualizarCrear() {

    }

    public void habilitarVerBorrar() {

    }

    public void borrar() {
        txtBuscar.setText("");
        txtContraseña.setText("");
        txtFecha.setText("");
        txtNombre.setText("");

        // tblUsuario.setModel(new DefaultTableModel());
    }

    public void crearUsuario() {
        nivel = (int) niveles.get(cboNivel.getSelectedIndex());
        ConectarDB conectar = new ConectarDB();
        con = conectar.coneccion();
        String fechaLimite = txtFecha.getText();

        String pass = txtContraseña.getText();
        String passEncriptado = DigestUtils.md5Hex(pass);
        int estado = 0;
        sSQL = "select curdate() <= '" + fechaLimite + "'";
        try {
            PreparedStatement pst = con.prepareStatement(sSQL);
            ResultSet rs = pst.executeQuery(sSQL);
            while (rs.next()) {
                estado = rs.getInt(1);
            }
            JOptionPane.showMessageDialog(null, estado);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "introduzca la fecha con formato yyyy,mmm,dd ");
        }

        sSQL = "insert into usuario(login, pass, estado, fecha, nivel)"
                + " values(?,?,?,?,?) ";
        try {
            PreparedStatement pst = con.prepareStatement(sSQL);
            //ResultSet rs = pst.executeQuery(sSQL);

            pst.setString(1, txtNombre.getText());
            pst.setString(2, passEncriptado);
            pst.setInt(3, estado);
            pst.setString(4, fechaLimite);
            pst.setInt(5, nivel);
            //pst.
            //pst.executeUpdate(sSQL);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(null, "Usuario creado");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error " + ex);
        }
    }

    public void actualizarUsuario() {
        celdaSeleccionada();
        //MetodosMenu01 metodo =new MetodosUsuarios();
        int editado = tblUsuario.getEditingRow();
        //String strs= tblUsuario.is
        ConectarDB conectar = new ConectarDB();
        con = conectar.coneccion();
        String fechaLimite = txtFecha.getText();
        int estado = 0;
        String sSQL = "";
        nivel = (int) niveles.get(cboNivel.getSelectedIndex());
//        sSQL = "select curdate() <= '" + fechaLimite + "'";
//
//        try {
//            PreparedStatement pst = con.prepareStatement(sSQL);
//            ResultSet rs = pst.executeQuery(sSQL);
//            while (rs.next()) {
//                estado = rs.getInt(1);
//            }
//        } catch (SQLException ex) {
//            JOptionPane.showMessageDialog(null, "introduzca la fecha con formato yyyy,mmm,dd ");
//        }
//        sSQL = "update usuario set login=?,password=?, estado=?, fecha=?, nivel=?"
//                + " where login = '"+usuarioSeleccionado+"' ";
//        
        
        //************************************
        ArrayList lista = new ArrayList();
        lista.add(txtNombre.getText());
        lista.add(DigestUtils.md5Hex(txtContraseña.getText()));
        lista.add(estado);
        lista.add(fechaLimite);
        lista.add(nivel);
        //************************************
        try {
            PreparedStatement pst = con.prepareStatement(sSQL);
            //ResultSet rs = pst.executeQuery(sSQL);

            pst.setString(1, txtNombre.getText());
            pst.setString(2, DigestUtils.md5Hex(txtContraseña.getText()));
            pst.setInt(3, estado);
            pst.setString(4, fechaLimite);
            pst.setInt(5, nivel);

            pst.executeUpdate();
            //pst.ex
            //pst.executeUpdate(sSQL);
            JOptionPane.showMessageDialog(null, "Usuario " +usuarioSeleccionado  + " Actualizado a "+ txtNombre.getText());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error " + ex);
        }
    }

    public void eliminarUsuario() {

    }

    public void seleccioNivel() {
        ConectarDB coneccion = new ConectarDB();

        con = coneccion.coneccion();

        sSQL = "select * from perfil order by 1 desc";

        //select permiso from perfil_permiso left join permiso on perfil_permiso.id_perfil='2'and  perfil_permiso.id_permiso=permiso.id;
        try {
            pst = con.prepareStatement(sSQL);
            rs = pst.executeQuery(sSQL);

            while (rs.next()) {

                cboNivel.addItem(rs.getString("descripcion"));
                niveles.add(rs.getInt("id"));
                //JOptionPane.showMessageDialog(null, niveles.get(0));

            }
            // pst.execute();

            //JOptionPane.showMessageDialog(null, "exito ");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error " + ex);
            //Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void agregarAccionBuscar(JTextField m) {
        m.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                comboBuscar();
                if (c == KeyEvent.VK_BACK_SPACE) {
                    comboBuscar();

                    //tblUsuario.setModel(new DefaultTableModel());
                }

            }
        });
    }

    public void comboBuscar() {
        //editadoFila = tblUsuario.getEditingRow();
       /// editadoColumna =tblUsuario.getEditingColumn();
        //int seleccionadoColumna = tblUsuario.getSelectedColumn();
        //int seleccionadoFila = tblUsuario.getSelectedRow();
        
        if (cboBuscar.getSelectedIndex() == 0) {
            MetodosUsuarios metodo = new MetodosUsuarios(txtBuscar.getText(), modelo, tblUsuario, serie,editadoFila,
                                        editadoColumna, serieSeleccionada, usuarioSeleccionado);
            metodo.buscar();
            stringSeleccion = "buscar";
            modelo= metodo.getModelo();
            // usuarioSeleccionado();
             metodo.seleccionCelda();
             metodo.enterCelda();
            // usuarioSeleccionado();
        } else if (cboBuscar.getSelectedIndex() == 1) {
             //usuarioSeleccionado = (String) serie.get(editadoFila);
            //serieSeleccionada=Integer.parseInt(serie.get(editadoFila).toString());
            MetodosUsuarios metodo = new MetodosUsuarios(txtBuscar.getText(), modelo, tblUsuario, serie,editadoFila,
                                        editadoColumna, serieSeleccionada, usuarioSeleccionado);
            metodo.verBitacora();
            //verBitacora();
            stringSeleccion = "bitacora";
            metodo.verBitacora();
            serie = metodo.getSerie();
            modelo= metodo.getModelo();
            metodo.enterCelda();
        }
    }
    /*
    public void usuarioSeleccionado(){
        if(tblUsuario.isFocusOwner()){
            usuarioSeleccionado = (String) modelo.getValueAt(editadoFila, 0);
        }
        else{
            JOptionPane.showMessageDialog(null, " seleccione una celda ");
        }
        
        //editadoFila = tblUsuario.getEditingRow();
        //editadoColumna =tblUsuario.getEditingColumn();
        //usuarioSeleccionado = (String) modelo.getValueAt(editadoFila, 0);
        
    }
*/
    public void celdaSeleccionada(){
        editadoFila = tblUsuario.getEditingRow();
        editadoColumna =tblUsuario.getEditingColumn();
         seleccionadoColumna = tblUsuario.getSelectedColumn();
        seleccionadoFila = tblUsuario.getSelectedRow();
        
        
        //JOptionPane.showMessageDialog(null,cboBuscar.getSelectedIndex());

        if (cboBuscar.getSelectedIndex()==0) {
           usuarioSeleccionado = (String) modelo.getValueAt(seleccionadoFila, 0);
           
          // JOptionPane.showMessageDialog(null, " " + seleccionadoFila + " " + seleccionadoColumna + " "+
            //    usuarioSeleccionado);
        } 
        else if (cboBuscar.getSelectedIndex()==1) {
            usuarioSeleccionado = (String) serie.get(editadoFila);
            serieSeleccionada=Integer.parseInt(serie.get(editadoFila).toString());
            
            
            //JOptionPane.showMessageDialog(null, " " + seleccionadoFila + " " + seleccionadoColumna + " "+
            //    usuarioSeleccionado+" "+ serieSeleccionada);
        }

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnMain = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuario = new javax.swing.JTable();
        btnSalida = new javax.swing.JButton();
        pn1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        txtNombre = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboNivel = new javax.swing.JComboBox<>();
        btnCrear = new javax.swing.JButton();
        pn2 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnBorrar = new javax.swing.JButton();
        cboBuscar = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        mPermiso = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblUsuario);

        btnSalida.setText("CerrarSesión");
        btnSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalidaActionPerformed(evt);
            }
        });

        pn1.setBorder(javax.swing.BorderFactory.createTitledBorder("Creción"));
        pn1.setToolTipText("");

        jLabel3.setText("Contraseña");

        jLabel4.setText("Nivel de Acceso");

        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        jLabel1.setText("Nombre de Usuario");

        jLabel2.setText("Fecha Expiracion Acceso ");

        btnCrear.setText("Crear /Actualizar");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn1Layout = new javax.swing.GroupLayout(pn1);
        pn1.setLayout(pn1Layout);
        pn1Layout.setHorizontalGroup(
            pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNivel, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCrear)
                .addContainerGap())
        );
        pn1Layout.setVerticalGroup(
            pn1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn1Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pn2.setBorder(javax.swing.BorderFactory.createTitledBorder("Busquedas"));
        pn2.setToolTipText("");

        jLabel5.setText("Parametros de busqueda");

        btnBorrar.setText("Limpiar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        cboBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Buscar Usuario", "Buscar Bitacora" }));
        cboBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn2Layout = new javax.swing.GroupLayout(pn2);
        pn2.setLayout(pn2Layout);
        pn2Layout.setHorizontalGroup(
            pn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pn2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBorrar))
                    .addGroup(pn2Layout.createSequentialGroup()
                        .addGroup(pn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 46, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pn2Layout.setVerticalGroup(
            pn2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pn2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
                .addComponent(btnBorrar)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnMainLayout = new javax.swing.GroupLayout(pnMain);
        pnMain.setLayout(pnMainLayout);
        pnMainLayout.setHorizontalGroup(
            pnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMainLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalida))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnMainLayout.createSequentialGroup()
                        .addComponent(pn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnMainLayout.setVerticalGroup(
            pnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnMainLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pn2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalida)
                .addContainerGap())
        );

        mPermiso.setText("Permisos");
        mPermiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mPermisoActionPerformed(evt);
            }
        });
        jMenuBar1.add(mPermiso);

        jMenu1.setText("Ayuda");

        jMenuItem1.setText("Ayuda");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalidaActionPerformed
        // TODO add your handling code here:
        suiche = true;
        registarSalida();


    }//GEN-LAST:event_btnSalidaActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
      celdaSeleccionada();
    }//GEN-LAST:event_btnBorrarActionPerformed


    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed

        // TODO add your handling code here:
        if (accionCrearActualizar.equals("crear")) {
            crearUsuario();
        } else if (accionCrearActualizar.equals("actualizar")) {
            actualizarUsuario();
        }
        //crearUsuario();
        // 


    }//GEN-LAST:event_btnCrearActionPerformed

    private void mPermisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mPermisoActionPerformed
        // TODO add your handling code here:
        //agregarAccionMenu(jMenuItem1);
    }//GEN-LAST:event_mPermisoActionPerformed

    private void cboBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboBuscarActionPerformed
        // TODO add your handling code here:
        MetodosUsuarios metodo= new MetodosUsuarios();
        txtBuscar.setText("");
        txtBuscar.grabFocus();
        comboBuscar();
    }//GEN-LAST:event_cboBuscarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "911");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnSalida;
    private javax.swing.JComboBox<String> cboBuscar;
    private javax.swing.JComboBox<String> cboNivel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenu mPermiso;
    private javax.swing.JPanel pn1;
    private javax.swing.JPanel pn2;
    private javax.swing.JPanel pnMain;
    private javax.swing.JTable tblUsuario;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
