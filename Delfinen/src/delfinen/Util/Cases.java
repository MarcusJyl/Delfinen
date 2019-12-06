package delfinen.Util;

import delfinen.Datamappers.DBBetaling;
import delfinen.Datamappers.DBBetalingStatusSpeci;
import delfinen.Datamappers.DBMedlem;
import delfinen.Datamappers.DBMedlemsOplysninger;
import delfinen.Datamappers.DBTræning;
import delfinen.Model.Medlem;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Cases {

    private ScannerFunc scannerFunc;

    public Cases(ScannerFunc scannerFunc) {
        this.scannerFunc = scannerFunc;
    }

    public void case1() {
        String navn1 = scannerFunc.getUserString("Indtast det nye medlems navn:");
        String fødselsdato1 = scannerFunc.getFøds("Indtast det nye medlems fødselsdato i følgende format: dd-mm-yyyy");
        int status1 = scannerFunc.getUserInteger("Hvilken status skal medlemmet have?\n1. Passiv\n2. Aktiv", 2, 1);
        int holdtype1 = scannerFunc.getUserInteger("Hvilket hold skal medlemmet tilmeldes?\n1. Motionist\n2. Konkurrence", 2, 1);
        InputHandler.lavMedlem(navn1, fødselsdato1, holdtype1, status1);

        scannerFunc.insertDiciplin(holdtype1);
    }

    public void case2() {
        int id = scannerFunc.getMedlemSomStarterMed();
        if (id != -1) {
            System.out.println("Kontingentet er på " + DBMedlem.getMedlemsKontingent(id) + ",-");
        }
    }

    public void case3() {
        String dato = scannerFunc.getFøds("Trænings dato:\nSkriv 1 for i dag eller indtast dato i følgende format: dd-mm-yyyy");
        if (dato.equals("1")) {
            LocalDate date = LocalDate.now();
            dato = date.toString();
        }
        int træningsType = scannerFunc.getUserInteger("Trænings type:\n1. Senior\n2. Junior", 2, 1);
        int træningsForm = scannerFunc.getUserInteger("Trænings form:\n1. Konkurrence\n2. Motion", 2, 1);

        scannerFunc.getDBT().insert(dato, scannerFunc.checkBoolFrom1Or2(træningsType), scannerFunc.checkBoolFrom1Or2(træningsForm));

        if (træningsForm == 1) {
            int opretRes = scannerFunc.getUserInteger("Ønskes der at opret resultater for denne træning?\n1. Ja\n2. Nej", 2, 1);
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
                    dicipliner.add("crawl");
                    dicipliner.add("brystsvømning");
                    dicipliner.add("rygcrawl");
                    dicipliner.add("butterfly");
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
                                String tid = scannerFunc.getUserString("Intast Tid i format mm:ss eller skriv stop hvis medlemet ikke blevt tidstaget i denne diciplin\nFor " + medlem.getNavn());
                                if (!tid.contains("stop")) {
                                    scannerFunc.getDBR().insert(lastTræningsId, medlem.getId(), tid, dicipliner.get(in));
                                }
                            }
                        }
                        dicipliner.remove(in);
                    }

                }
            }
        }
    }

    public static void case4() {
        ScannerFunc scannerFunc = new ScannerFunc();

        ArrayList<String> list = new ArrayList();
        String dici = "";
        while (!dici.contains("lukke")) {
            dici = vælgDiciplin(list);
            if (!dici.contains("lukke")) {
                String[][] arr = scannerFunc.getDBR().select(dici);
                for (int i = 0; i < arr.length; i++) {
                    System.out.println(" Medlemmets navn: " + arr[i][1] + "| Tid: " + arr[i][0]);
                }
            }
        }
    }

    public void case5() {
        ArrayList<Item> items = scannerFunc.getDBB().getBetalingsoversigt();
        for (Item item : items) {
            System.out.println(item);
        }
    }

    public void case6() {
        String status = "";
        int id = scannerFunc.getMedlemSomStarterMed();
        if (id != -1) {
            if (DBBetalingStatusSpeci.getMedlemsKontingent(id)) {
                status = " er ikke i restance";
            } else {
                status = " er i restance";
            }
            System.out.println("Medlemet " + status);
        }
    }

    public void case7() {
        int medlemsId = scannerFunc.getMedlemSomStarterMed();
        if (medlemsId != -1) {
            Medlem item = DBMedlemsOplysninger.medlemsOplysninger(medlemsId);
            System.out.println(item);
            //System.out.println(item.getHoldtype() + " " + item.getKontingent() + " " + item.getKontingentStatus() + " " + item.getStatus1());
        }
    }

    public void case8() {
        int holdId = scannerFunc.getDBH().størsteId() + 1;

        String navn8 = scannerFunc.getUserString("Skriv stævnets navn:");
        String dato8 = scannerFunc.getFøds("Indtast dato på stævnet i følgende format: dd-mm-yyyy");
        scannerFunc.getDBS().insert(navn8, dato8);

        int stævneId = scannerFunc.getDBS().størsteId();
        int antalMedlemmerPåHoldet = scannerFunc.getUserInteger("Hvor mange svømmer er der på holdet", 10, 2);
        for (int i = 0; i < antalMedlemmerPåHoldet; i++) {
            int medlemsId = scannerFunc.getMedlemSomStarterMed();
            if (medlemsId != -1) {
                scannerFunc.getDBH().insert(holdId, medlemsId, stævneId);
            }
        }
    }

    public static String vælgDiciplin(ArrayList<String> list) {
        Scanner input = new Scanner(System.in);
        ArrayList<String> dicipliner = new ArrayList();
        dicipliner.add("crawl");
        dicipliner.add("brystsvømning");
        dicipliner.add("rygcrawl");
        dicipliner.add("butterfly");
        dicipliner.add("For at lukke");
        for (String dici : list) {
            dicipliner.remove(dici);
        }
        for (int i = 0; i < dicipliner.size(); i++) {
            System.out.println(i + ". " + dicipliner.get(i));
        }

        int in = input.nextInt();
        input.nextLine();

        list.add(dicipliner.get(in));
        return dicipliner.get(in);
    }

}
