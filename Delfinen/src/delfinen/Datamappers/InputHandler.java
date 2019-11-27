package delfinen.Datamappers;

public class InputHandler {

    public static void lavMedlem(String navn, String fødselsDato, int holdtype) {
        if (holdtype == 1) {
            DBMedlem.insert(navn, fødselsDato, "motionist");
        } else if (holdtype == 2) {
            DBMedlem.insert(navn, fødselsDato, "Konkurrence");
        }
    }

}
