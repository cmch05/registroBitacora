/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainProyect.mundo;

import java.io.File;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import mainProyect.reporte.GenerarReporteTabla;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author cmch05
 */
public class GenerarReporte {
    private ArrayList lista = new ArrayList();
    private JTable tblUsuario;
    private String id="",fSalida="",fEntrada="",hSalida="",hEntrada="";
    public GenerarReporte(JTable tblUsuario) {
        this.tblUsuario = tblUsuario;
    }
    
    
    public void reporte(){
        
        
        
        
        
        
        for (int i = 0; i < tblUsuario.getRowCount(); i++) {
            id= tblUsuario.getValueAt(0, i).toString();
            fEntrada= tblUsuario.getValueAt(1, i).toString();
            fSalida= tblUsuario.getValueAt(3, i).toString();
            hEntrada= tblUsuario.getValueAt(2, i).toString();
            hSalida= tblUsuario.getValueAt(4, i).toString();
            GenerarReporteTabla generar= new GenerarReporteTabla(id, fSalida, fEntrada, hSalida, hEntrada);
            lista.add(generar);
        }
        
        try {
            JasperReport reportes =(JasperReport) JRLoader.loadObjectFromFile("reporteTabla.jasper");
            Map parametro = new HashMap();
            parametro.put("ID", "ID");
            JasperPrint jprint = JasperFillManager.fillReport(reportes, parametro, new JRBeanCollectionDataSource(lista));
            JasperViewer.viewReport(jprint);
        } 
        catch (JRException ex) {
            Logger.getLogger(GenerarReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
