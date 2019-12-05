package delfinen.Controllers;

import delfinen.Util.Cases;
import delfinen.Util.ScannerFunc;
import delfinen.View.MainMenuUI;


public class Controller {

    public static void runMainMenu() {
        ScannerFunc scanner = new ScannerFunc();
        Cases cases = new Cases(scanner);
        int svar = 0;

        while (svar != 9) {
            svar = scanner.getUserInteger(MainMenuUI.showMainMenu(), 9, 1);
            switch (svar) {
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
                    cases.case6();
                    break;
                case 8:
                    cases.case8();
                    break;
                case 9:
                    System.exit(0);
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

    
}
