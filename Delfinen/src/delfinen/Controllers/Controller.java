package delfinen.Controllers;

import delfinen.Datamappers.DBMedlemsOplysninger;
import delfinen.Model.Medlem;
import delfinen.Util.Cases;
import delfinen.Util.ScannerFunc;
import delfinen.View.MainMenuUI;
import java.util.ArrayList;

public class Controller {

    public static void runMainMenu() {
        ScannerFunc scanner = new ScannerFunc();
        Cases cases = new Cases(scanner);
        int svar = 11;

        while (svar != 0) {

            svar = scanner.getUserInteger(MainMenuUI.showMainMenu(), 9, 1);

            switch (svar) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    cases.case1();
                    break;
                case 2:
                    cases.case2();
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
                    cases.case7();
                    break;
                case 8:
                    cases.case8();
                    break;
                case 9:
                    ArrayList<Integer> stævneIder = scanner.getDBH().getHold();
                    for (Integer integer : stævneIder) {
                        System.out.println(scanner.getDBS().getStævne(integer));
                    }
                    int holdId = scanner.getUserInteger("Skriv nummeret på det ønsket stævne: ", stævneIder.size(), 0);
                    ArrayList<Integer> medlemsIder = scanner.getDBH().getMedlemmer(holdId);
                    ArrayList<Medlem> medlemmer = new ArrayList();
                    int i = 0;
                    for (Integer id : medlemsIder) {
                        Medlem medlem = DBMedlemsOplysninger.medlemsOplysninger(id);
                        medlem.setDicipliner(scanner.getDBD().selectById(id));
                        medlemmer.add(medlem);
                    }
                    for (Medlem medlem : medlemmer) {
                        i++;
                        System.out.println(i + ". " + medlem + "| Dicipliner: " + medlem.getDicipliner());
                    }
                    break;
            }
            System.out.println("************************************************\nSkriv hvad som helst for at gå til hoved menuen igen:");
            scanner.getInput().nextLine();
        }
    }
}
