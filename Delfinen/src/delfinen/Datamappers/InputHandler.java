package delfinen.Datamappers;

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
    
    public static void getDouble(String føds, int status) {
        Date date = new Date();
        System.out.println(date);
    }

}
