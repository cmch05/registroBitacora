
package mainProyect;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class formatoFecha {
    private static final String DATE_PATTERN="yyyy.mm.dd";
    
    private static final DateTimeFormatter DATE_FORMATTER=
           DateTimeFormatter.ofPattern(DATE_PATTERN);
            
    public static String formato(LocalDate date){
        if(date==null){
            return null;
        }
        return DATE_FORMATTER.format(date);
        
    }
    public static LocalDate parse(String dateString){
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (Exception e) {
            return null;
        }
    }
    public static boolean validarFecha(String dateString){
        return formatoFecha.parse(dateString) != null;
    }
    
}
    

