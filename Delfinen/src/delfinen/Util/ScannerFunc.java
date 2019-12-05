package delfinen.Util;

import delfinen.Datamappers.DBBetaling;
import delfinen.Datamappers.DBDiciplin;
import delfinen.Datamappers.DBHold;
import delfinen.Datamappers.DBMedlem;
import delfinen.Datamappers.DBResultat;
import delfinen.Datamappers.DBStævne;
import delfinen.Datamappers.DBTræning;
import delfinen.Model.Medlem;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author marcg
 */
public class ScannerFunc {

    private Scanner input;

    private DBDiciplin DBD = new DBDiciplin();
    private DBResultat DBR = new DBResultat();
    private DBMedlem DBM = new DBMedlem("medlems_id", "medlemmer");
    private DBTræning DBT = new DBTræning("trænings_id", "træning");
    private DBBetaling DBB = new DBBetaling();
    private DBHold DBH = new DBHold("hold_id", "hold");
    private DBStævne DBS = new DBStævne("stævne_id", "stævne");

    public DBHold getDBH() {
        return DBH;
    }

    public DBStævne getDBS() {
        return DBS;
    }

    public DBBetaling getDBB() {
        return DBB;
    }

    public Scanner getInput() {
        return input;
    }

    public DBDiciplin getDBD() {
        return DBD;
    }

    public DBResultat getDBR() {
        return DBR;
    }

    public DBMedlem getDBM() {
        return DBM;
    }

    public DBTræning getDBT() {
        return DBT;
    }

    public ScannerFunc() {
        this.input = new Scanner(System.in);
    }

    public String getUserString(String tmp) {
        String retVal = "";
        System.out.println(tmp);
        retVal = input.nextLine();
        return retVal;
    }

    public int getUserInteger(String tmp, int max, int min) {
        int retVal = 0;
        boolean error = true;
        System.out.println(tmp);
        do {
            try {
                Scanner myScan = new Scanner(System.in);
                retVal = myScan.nextInt();
                myScan.nextLine();
                error = false;
            } catch (Exception e) {
                System.out.println("Ikke valid input det skal være et helt tal mellem " + max + " og " + min);
                error = true;
            }
        } while (error);

        if (retVal <= max && retVal >= min) {
            return retVal;
        } else {
            return getUserInteger("Ikke valid input det skal være et helt tal mellem " + max + " og " + min, max, min);
        }
    }

    public boolean checkIfStringParseInt(String input) {
        try {
            Integer.parseInt(input);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getÅr(String tekst) {
        String år = getUserString(tekst);
        if (år.length() != 4 || !checkIfStringParseInt(år)) {
            getÅr(tekst);
        }
        return år;
    }

    public String getFøds(String tekst) {
        String retVal = "";
        System.out.println(tekst);
        retVal = input.nextLine();
        try {
            String[] dato = retVal.split("-");

            for (String string : dato) {
                if (!checkIfStringParseInt(string)) {
                    return getFøds("Invalid input:\n" + tekst);
                }
            }

            if (dato[0].length() == 1) {
                dato[0] = "0" + dato[0];
            }
            if (dato[1].length() == 1) {
                dato[1] = "0" + dato[1];
            }
            if (dato[2].length() != 4) {
                dato[2] = this.getÅr("Forkert års tal indtast et 4 cifret tal");
            }
            retVal = dato[2] + "-" + dato[1] + "-" + dato[0];
            System.out.println(retVal);
        } catch (Exception e) {
            if (retVal.equals("1")) {
                return retVal;
            } else {
                getFøds("Forkert format:\n" + tekst);
            }
        }
        return retVal;
    }

    public void insertDiciplin(int holdtype) {
        ArrayList<String> dicipliner = new ArrayList();
        dicipliner.add("Crawl");
        dicipliner.add("Brystsvømning");
        dicipliner.add("Rygcrawl");
        dicipliner.add("Butterfly");
        dicipliner.add("For at lukke");

        if (holdtype == 2) {
            int in = 0;
            while (in != dicipliner.size()) {
                if (dicipliner.isEmpty()) {
                    return;
                }
                for (int i = 0; i < dicipliner.size(); i++) {
                    System.out.println(i + ". " + dicipliner.get(i));
                }
                in = input.nextInt();
                input.nextLine();
                int id = DBM.størsteId();
                if (in != dicipliner.size() - 1) {
                    DBD.insert(id, dicipliner.get(in));
                }
                dicipliner.remove(in);
            }
        }
    }

    public int getMedlemSomStarterMed() {
        String navn = getUserString("Skriv svømmers navn:");

        ArrayList<Medlem> medlemmer = getDBM().getAlleMedlemmerDerStarterMed(navn);
        int j = 0;
        for (Medlem medlem : medlemmer) {
            j++;
            System.out.println(j + ". " + "Navn: " + medlem.getNavn() + "| Fødselsdag: " + medlem.getFødselsdato());
        }
        int nummer = getUserInteger("Skrive nummer på svømmer", j, 1);
        return medlemmer.get(nummer - 1).getId();
    }

    public static boolean checkBoolFrom1Or2(int tal) {
        if (tal == 1) {
            return true;
        }
        return false;
    }
}
