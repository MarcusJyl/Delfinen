package delfinen.Datamappers;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class InputHandler {

    public static void lavMedlem(String navn, String fødselsDato, int holdtype, int status) {
        if (holdtype == 1) {
            if (status == 1) {
                DBMedlem.insert(navn, fødselsDato, "motionist", "passiv");
            } else {
                DBMedlem.insert(navn, fødselsDato, "motionist", "aktiv");
            }

        } else if (holdtype == 2) {
            if (status == 1) {
                DBMedlem.insert(navn, fødselsDato, "Konkurrence", "passiv");
            } else {
                DBMedlem.insert(navn, fødselsDato, "Konkurrence", "aktiv");
            }
        }
    }
    
   public static int getAlder(String føds) {
       String[] dato = føds.split("-");
       int[] datoTal = new int[dato.length];
       for (int i = 0; i < dato.length; i++) {
           datoTal[i] = Integer.parseInt(dato[i]);
       }
       
        LocalDate today = LocalDate.now();                         
        LocalDate birthday = LocalDate.of(datoTal[0],datoTal[1], datoTal[2]); 
        Period p = Period.between(birthday, today);
       return p.getYears();
   }
}
