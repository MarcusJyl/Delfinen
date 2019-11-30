package delfinen.Util;

import java.util.Scanner;

/**
 *
 * @author marcg
 */
public class ScannerFunc {
    
    Scanner input;

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

}
