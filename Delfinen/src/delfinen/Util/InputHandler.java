package delfinen.Util;

import delfinen.Datamappers.DBMedlem;
import java.time.LocalDate;
import java.time.Period;

public class InputHandler {
    
    public static void lavMedlem(String navn, String fødselsDato, int holdtype, int status) {
        DBMedlem DBM = new DBMedlem("sdasda", "dasdasda");
        if (holdtype == 1) {
            if (status == 1) {
                DBM.insert(navn, fødselsDato, "motionist", "passiv", beregnKotingent(fødselsDato, status));
            } else {
                DBM.insert(navn, fødselsDato, "motionist", "aktiv",beregnKotingent(fødselsDato, status));
            }

        } else if (holdtype == 2) {
            if (status == 1) {
                DBM.insert(navn, fødselsDato, "Konkurrence", "passiv", beregnKotingent(fødselsDato, status));
            } else {
                DBM.insert(navn, fødselsDato, "Konkurrence", "aktiv", beregnKotingent(fødselsDato, status));
            }
        }
    }

    public static double beregnKotingent(String føds, int status) {
        int alder = getAlder(føds);
        double kontingent = 500;
        if (status == 2) {
            if (alder < 18) {
                kontingent = 1000;
            } else if (alder < 60) {
                kontingent = 1600;
            } else {
                kontingent = 1600 * 0.75;
            }
        }
        return kontingent;

    }

    public static int getAlder(String føds) {
        String[] dato = føds.split("-");
        int[] datoTal = new int[dato.length];
        for (int i = 0; i < dato.length; i++) {
            datoTal[i] = Integer.parseInt(dato[i]);
        }

        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(datoTal[0], datoTal[1], datoTal[2]);
        Period p = Period.between(birthday, today);
        return p.getYears();
    }
}
    