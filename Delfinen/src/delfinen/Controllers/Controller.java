package delfinen.Controllers;

import delfinen.Datamappers.DBDiciplin;
import delfinen.Datamappers.DBMedlem;
import delfinen.Datamappers.InputHandler;
import delfinen.View.MainMenuUI;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    static Scanner input = new Scanner(System.in);

    public static void runMainMenu() {

        int svar = 0;

        while (svar != 9) {
            MainMenuUI.showMainMenu();
            svar = input.nextInt();
            input.nextLine();
            switch (svar) {
                case 1:
                    String navn = getUserString("Indtast det nye medlems navn:");
                    String fødselsdato = getUserString("Indtast det nye medlems fødselsdato i følgende format: yyyy-mm-dd");
                    int status = getUserInteger("Hvilken status skal medlemmet have?\n1. Passiv\n2. Aktiv");
                    int holdtype = getUserInteger("Hvilket hold skal medlemmet tilmeldes?\n1. Motionist\n2. Konkurrence");
                    InputHandler.lavMedlem(navn, fødselsdato, holdtype, status);

                    getDiciplin(holdtype);
                    break;
            }
        }
    }

    public static String getUserString(String tmp) {
        String retVal = "";
        System.out.println(tmp);
        retVal = input.nextLine();
        return retVal;
    }

    public static int getUserInteger(String tmp) {
        int retVal = 0;
        System.out.println(tmp);
        retVal = input.nextInt();
        input.nextLine();
        return retVal;
    }

    public static void getDiciplin(int holdtype) {
        ArrayList<String> dicipliner = new ArrayList();
        dicipliner.add("Crawl");
        dicipliner.add("Brystsvømning");
        dicipliner.add("Rygcrawl");
        dicipliner.add("Butterfly");
        dicipliner.add("For at lukke");

        if (holdtype == 2) {
            int in = 0;
            while (in != dicipliner.size()) {
                for (int i = 0; i < dicipliner.size(); i++) {
                    System.out.println(i + ". " + dicipliner.get(i));
                }
                in = input.nextInt();
                input.nextLine();
                int id = DBMedlem.størsteMedlemsId();
                if (in != dicipliner.size() - 1) {
                    DBDiciplin.insert(id, dicipliner.get(in));
                }
                dicipliner.remove(in);
            }
        }
    }
}
