package delfinen.Controllers;

import delfinen.Datamappers.DBDiciplin;
import delfinen.Datamappers.DBMedlem;
import delfinen.Datamappers.DBResultat;
import delfinen.Datamappers.InputHandler;
import delfinen.View.MainMenuUI;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    public static void runMainMenu() {

        int svar = 0;

        while (svar != 9) {
            svar = getUserInteger(showMainMenu());
            switch (svar) {
                case 1:
                    String navn1 = getUserString("Indtast det nye medlems navn:");
                    String fødselsdato1 = getFøds("Indtast det nye medlems fødselsdato i følgende format: dd-mm-yyyy");
                    int status1 = getUserInteger("Hvilken status skal medlemmet have?\n1. Passiv\n2. Aktiv");
                    int holdtype1 = getUserInteger("Hvilket hold skal medlemmet tilmeldes?\n1. Motionist\n2. Konkurrence");
                    InputHandler.lavMedlem(navn1, fødselsdato1, holdtype1, status1);

                    getDiciplin(holdtype1);
                    break;
                case 2:
                    String navn2 = getUserString("Indtast medlems navn:");
                    String fødselsdato2 = getFøds("Indtast det nye medlems fødselsdato i følgende format: dd-mm-yyyy");

                    System.out.println("Kontingentet er på " + DBMedlem.getMedlemsKontingent(navn2, fødselsdato2) + ",-");
                    break;
                case 3:
                    int træningsType = getUserInteger("1. Junior\n2. Senior");
                    
                    break;

            }
        }
    }
    
    public static void opretRes(){
        Scanner input = new Scanner(System.in);
    }

    public static String getUserString(String tmp) {
        Scanner input = new Scanner(System.in);
        String retVal = "";
        System.out.println(tmp);
        retVal = input.nextLine();
        return retVal;
    }

    public static int getUserInteger(String tmp) {
        Scanner input = new Scanner(System.in);
        int retVal = 0;
        System.out.println(tmp);
        retVal = input.nextInt();
        input.nextLine();
        return retVal;
    }

    public static String getFøds(String tekst) {
        Scanner input = new Scanner(System.in);
        String retVal = "";
        System.out.println(tekst);
        retVal = input.nextLine();
        String[] dato = retVal.split("-");
        retVal = dato[2] + "-" + dato[1] + "-" + dato[0];
        return retVal;
    }

    public static void getDiciplin(int holdtype) {
        Scanner input = new Scanner(System.in);
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

    public static String showMainMenu() {
        return "************************************************\n"
                + "Velkommen til Delfinen svømmeklub\n"
                + "Muligheder:\n"
                + "1. Opret nyt medlem\n"
                + "2. Se kontingent\n"
                + "3. Opret trænings resultater\n"
                + "4. TBM\n"
                + "************************************************\n";
    }
}
