package mainProyect.reporte;

/**
 *
 * @author cmch05
 */
public class GenerarReporteTabla {
    private String id, fSalida,fEntrada,hSalida,hEntrada;

    public GenerarReporteTabla(String id, String fSalida, String fEntrada, String hSalida, String hEntrada) {
        this.id = id;
        this.fSalida = fSalida;
        this.fEntrada = fEntrada;
        this.hSalida = hSalida;
        this.hEntrada = hEntrada;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getfSalida() {
        return fSalida;
    }

    public void setfSalida(String fSalida) {
        this.fSalida = fSalida;
    }

    public String getfEntrada() {
        return fEntrada;
    }

    public void setfEntrada(String fEntrada) {
        this.fEntrada = fEntrada;
    }

    public String gethSalida() {
        return hSalida;
    }

    public void sethSalida(String hSalida) {
        this.hSalida = hSalida;
    }

    public String gethEntrada() {
        return hEntrada;
    }

    public void sethEntrada(String hEntrada) {
        this.hEntrada = hEntrada;
    }
    
    
}
