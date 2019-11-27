
package delfinen.Controllers;

import delfinen.View.MainMenuUI;
import java.util.Scanner;

public class Controller {
    static Scanner input = new Scanner(System.in);
    public void runMainMenu() {
        MainMenuUI.showMainMenu();
        int svar = input.nextInt();
        
        while(svar != 9) {
            switch(svar) {
                case 1:
                    String navn = getUserString("Indtast det nye medlems navn:");
                    int fødselsdato = getUserInteger("Indtast det nye medlems fødselsdato i følgende format: yyyymmdd");
                    int temp = getUserInteger("Hvilket hold skal medlemmet tilmeldes?\n1. Motionist\n2.Konkurrence");
                    if(temp == 1) {
                        String hold = "Motionist";
                    }
                    if(temp == 2) {
                        String hold = "Konkurrence";
                    }
                    
            }
        }
    }
    
    public static String getUserString(String tmp) {
        System.out.println(tmp);
        return input.nextLine();
    }
    
    public static int getUserInteger(String tmp) {
        int retVal = 0;
        System.out.println(tmp);
        retVal = input.nextInt();
        input.nextLine();
        return retVal;
    }
}
