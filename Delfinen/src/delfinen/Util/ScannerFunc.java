package delfinen.Util;

import delfinen.Datamappers.DBBetaling;
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
    private DBBetaling DBB = new DBBetaling();

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

    public int getUserInteger(String tmp) {
        int retVal = 0;
        System.out.println(tmp);
        retVal = input.nextInt();
        input.nextLine();
        return retVal;
    }

    public String getFøds(String tekst) {
        String retVal = "";
        System.out.println(tekst);
        retVal = input.nextLine();
        try {
            String[] dato = retVal.split("-");
            retVal = dato[2] + "-" + dato[1] + "-" + dato[0];
        } catch (Exception e) {
            if (retVal.equals("1")) {
                return retVal;
            } else {
                getFøds("Forkert format:\nIndtast medlems fødselsdato i følgende format: dd-mm-yyyy");
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
