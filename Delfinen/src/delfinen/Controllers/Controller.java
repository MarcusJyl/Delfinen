package delfinen.Controllers;

import delfinen.Datamappers.DBBetalingStatusSpeci;
import delfinen.Datamappers.DBDiciplin;
import delfinen.Datamappers.DBMedlem;
import delfinen.Datamappers.DBMedlemsOplysninger;
import delfinen.Datamappers.DBResultat;
import delfinen.Datamappers.DBTræning;
import delfinen.Datamappers.InputHandler;
import delfinen.Model.Medlem;
import delfinen.Util.Cases;
import delfinen.Util.Item;
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
            svar = scanner.getUserInteger(showMainMenu(), 9, 1);
            switch (svar) {
                case 1:
                    String navn1 = scanner.getUserString("Indtast det nye medlems navn:");
                    String fødselsdato1 = scanner.getFøds("Indtast det nye medlems fødselsdato i følgende format: dd-mm-yyyy");
                    int status1 = scanner.getUserInteger("Hvilken status skal medlemmet have?\n1. Passiv\n2. Aktiv", 2, 1);
                    int holdtype1 = scanner.getUserInteger("Hvilket hold skal medlemmet tilmeldes?\n1. Motionist\n2. Konkurrence", 2 , 1);
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
                    cases.case6();    
                    break;
                case 7:
                    Item item = DBMedlemsOplysninger.medlemsOplysninger();
                    System.out.println(item.getHoldtype() + " " + item.getKontingent() + " " + item.getKontingentStatus() + " " + item.getStatus1());
                    break;
                case 8:
                    int holdId = scanner.getDBH().størsteId() + 1;
                    
                    String navn8 = scanner.getUserString("Skriv stævnets navn:");
                    String dato8 = scanner.getFøds("Indtast dato på stævnet i følgende format: dd-mm-yyyy");
                    scanner.getDBS().insert(navn8, dato8);
                    
                    int stævneId = scanner.getDBS().størsteId();
                    int antalMedlemmerPåHoldet = scanner.getUserInteger("Hvor mange svømmer er der på holdet", 10, 2);
                    for (int i = 0; i < antalMedlemmerPåHoldet; i++) {
                        String navn = scanner.getUserString("Skriv svømmers navn:");
                        
                        ArrayList<Medlem> medlemmer = scanner.getDBM().getAlleMedlemmerDerStarterMed(navn);
                        int j = 0;
                        for (Medlem medlem : medlemmer) {
                            j++;
                            System.out.println(j + ". " + "Navn: " + medlem.getNavn() + "| Fødselsdag: " + medlem.getFødselsdato());
                        }
                        int nummer = scanner.getUserInteger("Skrive nummer på svømmer", j, 1);
                        int medlemsId = medlemmer.get(nummer - 1).getId();
                        scanner.getDBH().insert(holdId, medlemsId, stævneId);
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

    public static String showMainMenu() {
        return "************************************************\n"
                + "Velkommen til Delfinen svømmeklub\n"
                + "Muligheder:\n"
                + "1. Opret nyt medlem\n"
                + "2. Se kontingent\n"
                + "3. Opret trænings resultater\n"
                + "4. Se top svømmer\n"
                + "5. Se Betalingsoversigt\n"
                + "6. TBM\n"
                + "7. TBM\n"
                + "6. Se betalings status\n"
                + "7. Medlems oplysninger\n"
                + "8. opret stævne og hold til det\n"
                + "************************************************";

    }
}
