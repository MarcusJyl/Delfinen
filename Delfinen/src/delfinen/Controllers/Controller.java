package delfinen.Controllers;

import delfinen.Datamappers.DBDiciplin;
import delfinen.Datamappers.DBMedlem;
import delfinen.Datamappers.DBMedlemsOplysninger;
import delfinen.Datamappers.DBResultat;
import delfinen.Datamappers.DBTræning;
import delfinen.Datamappers.InputHandler;
import delfinen.Util.Cases;
import delfinen.Util.ScannerFunc;
import delfinen.View.MainMenuUI;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    public static void runMainMenu() {
        ScannerFunc scanner = new ScannerFunc();
        Cases cases = new Cases(scanner);
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

                    scanner.insertDiciplin(holdtype1);
                    break;
                case 2:
                    String navn2 = scanner.getUserString("Indtast medlems navn:");
                    String fødselsdato2 = scanner.getFøds("Indtast medlems fødselsdato i følgende format: dd-mm-yyyy");

                    System.out.println("Kontingentet er på " + DBMedlem.getMedlemsKontingent(navn2, fødselsdato2) + ",-");
                    break;
                case 3:
                    cases.case3();
                    break;
                case 4:
                    cases.case4();
                    break;
                case 5:
                    cases.case5();
                    break;
                    
                case 6:
                    DBMedlemsOplysninger test = new DBMedlemsOplysninger();
                    System.out.println(test.medlemsOplysninger());
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

    public static String showMainMenu() {
        return "************************************************\n"
                + "Velkommen til Delfinen svømmeklub\n"
                + "Muligheder:\n"
                + "1. Opret nyt medlem\n"
                + "2. Se kontingent\n"
                + "3. Opret trænings resultater\n"
                + "4. Se top svømmer\n"
                + "5. TBM\n"
                + "6. TBM\n"
                + "7. TBM\n"
                + "************************************************\n";
    }
}
