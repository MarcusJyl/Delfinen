package delfinen.Util;

import static delfinen.Controllers.Controller.checkBoolFrom1Or2;
import delfinen.Datamappers.DBTræning;
import delfinen.Model.Medlem;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Cases {

    private ScannerFunc scannerFunc;

    public Cases(ScannerFunc scannerFunc) {
        this.scannerFunc = scannerFunc;
    }

    public void case3() {
        String dato = scannerFunc.getFøds("Trænings dato:\nSkriv 1 for i dag eller indtast dato i følgende format: dd-mm-yyyy");
        if (dato.equals("1")) {
            LocalDate date = LocalDate.now();
            dato = date.toString();
        }
        int træningsType = scannerFunc.getUserInteger("Trænings type:\n1. Senior\n2. Junior");
        int træningsForm = scannerFunc.getUserInteger("Trænings form:\n1. Konkurrence\n2. Motion");

        scannerFunc.getDBT().insert(dato, checkBoolFrom1Or2(træningsType), checkBoolFrom1Or2(træningsForm));

        if (træningsForm == 1) {
            int opretRes = scannerFunc.getUserInteger("Ønskes der at opret resultater for denne træning?\n1. Ja\n2. Nej");
            if (opretRes == 1) {
                if (træningsType == 1) {
                    int lastTræningsId = scannerFunc.getDBT().størsteId();
                    long y = 18;
                    LocalDate date18 = LocalDate.now().minus(18, ChronoUnit.YEARS);
                    ArrayList<Medlem> medlemmer = scannerFunc.getDBM().getWhereAlderUnderEllerOver(">" + dato);

                    for (Medlem medlem : medlemmer) {
                        medlem.setDicipliner(scannerFunc.getDBD().selectById(medlem.getId()));
                    }

                    ArrayList<String> dicipliner = new ArrayList();
                    dicipliner.add("Crawl");
                    dicipliner.add("Brystsvømning");
                    dicipliner.add("Rygcrawl");
                    dicipliner.add("Butterfly");
                    dicipliner.add("For at lukke");

                    int in = 0;
                    while (in != dicipliner.size()) {
                        for (int i = 0; i < dicipliner.size(); i++) {
                            System.out.println(i + ". " + dicipliner.get(i));
                        }
                        in = scannerFunc.getInput().nextInt();
                        scannerFunc.getInput().nextLine();
                        int id = scannerFunc.getDBM().størsteId();
                        if (in == dicipliner.size() - 1) {
                            return;
                        }

                        for (Medlem medlem : medlemmer) {
                            if (medlem.getDicipliner().contains(dicipliner.get(in))) {
                                String tid = scannerFunc.getUserString("Intast Tid i format mm:ss:\nFor " + medlem.getNavn());
                                scannerFunc.getDBR().insert(lastTræningsId, medlem.getId(), tid, dicipliner.get(in));
                            }
                        }
                        dicipliner.remove(in);
                    }

                }
            }
        }
    }
}
