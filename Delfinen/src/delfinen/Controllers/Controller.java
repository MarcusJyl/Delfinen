package delfinen.Controllers;

import delfinen.Datamappers.DBDiciplin;
import delfinen.Datamappers.DBMedlem;
import delfinen.Datamappers.DBResultat;
import delfinen.Datamappers.DBTræning;
import delfinen.Datamappers.InputHandler;
import delfinen.Util.ScannerFunc;
import delfinen.View.MainMenuUI;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    public static void runMainMenu() {
        ScannerFunc scanner = new ScannerFunc();
        int svar = 0;

        while (svar != 9) {
            svar = scanner.getUserInteger(showMainMenu());
            switch (svar) {
                case 1:
                    String navn1 = scanner.getUserString("Indtast det nye medlems navn:");
                    String fødselsdato1 = scanner.getFøds("Indtast det nye medlems fødselsdato i følgende format: dd-mm-yyyy");
                    int status1 = scanner.getUserInteger("Hvilken status skal medlemmet have?\n1. Passiv\n2. Aktiv");
                    int holdtype1 = scanner.getUserInteger("Hvilket hold skal medlemmet tilmeldes?\n1. Motionist\n2. Konkurrence");
                    InputHandler.lavMedlem(navn1, fødselsdato1, holdtype1, status1);

                    getDiciplin(holdtype1);
                    break;
                case 2:
                    String navn2 = scanner.getUserString("Indtast medlems navn:");
                    String fødselsdato2 = scanner.getFøds("Indtast medlems fødselsdato i følgende format: dd-mm-yyyy");

                    System.out.println("Kontingentet er på " + DBMedlem.getMedlemsKontingent(navn2, fødselsdato2) + ",-");
                    break;
                case 3:
                    String dato = scanner.getFøds("Trænings dato:\nSkriv 1 for i dag eller indtast dato i følgende format: dd-mm-yyyy");
                    if (dato.equals("1")) {
                        LocalDate date = LocalDate.now();
                        dato = date.toString();
                    }
                    int træningsType = scanner.getUserInteger("Trænings type:\n1. Senior\n2. Junior");
                    int træningsForm = scanner.getUserInteger("Trænings form:\n1. Konkurrence\n2. Motion");

                    DBTræning.insert(dato, checkBoolFrom1Or2(træningsType), checkBoolFrom1Or2(træningsForm));

                    if (træningsForm == 1) {
                        int opretRes = scanner.getUserInteger("Ønskes der at opret resultater for denne træning?\n1. Ja\n2. Nej");
                        if (opretRes == 1) {
                            System.out.println(DBTræning.getNyesteTræningsId());
                            long y = 18;
                            LocalDate date18 = LocalDate.now().minus(18, ChronoUnit.YEARS);
                            System.out.println(date18);
                        }
                    }

                    break;

            }
        }
    }

    public static boolean checkBoolFrom1Or2(int tal) {
        if (tal == 1) {
            return true;
        }
        return false;
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
