package delfinen.Util;

import delfinen.Datamappers.DBDiciplin;
import delfinen.Datamappers.DBMedlem;
import delfinen.Datamappers.DBResultat;
import delfinen.Datamappers.DBTræning;
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
                System.out.println("Ikke vaild input det skal være et helt tal melle " + max + " og " + min);
                error = true;
            }
        } while (error);

        if (retVal <= max && retVal >= min) {
            return retVal;
        } else {
            return getUserInteger("Ikke vaild input det skal være et helt tal melle " + max + " og " + min, max, min);
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
    
    public String getÅr(String tekst){
        String år = getUserString(tekst);
        if(år.length() != 4 || !checkIfStringParseInt(år)){
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
            retVal = dato[2] + "-" + dato[1] + "-" + dato[0];
            
            for (String string : dato) {
                if(!checkIfStringParseInt(string)){
                    return getFøds("Invalid input:\n" + tekst);
                }
            }
            
            
            if (dato[2].length() == 1) {
                dato[2] = "0" + dato[2];
            }
            if (dato[1].length() == 1) {
                dato[1] = "0" + dato[1];
            }
            if (dato[0].length() != 4) {
                dato[0] = this.getÅr("Forkert års tal indtast et 4 cifret tal");
            }
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
}
