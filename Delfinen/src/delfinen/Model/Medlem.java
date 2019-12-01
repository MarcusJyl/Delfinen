
package delfinen.Model;

import java.util.ArrayList;

/*
@author Marcus Jensen
*/  
public class Medlem {

    private int id;
    private String navn;
    private String fødselsdato;
    private String holdtype;
    private ArrayList<String> dicipliner = new ArrayList();

    public Medlem(int id, String navn, String fødselsdato, String holdtype) {
        this.id = id;
        this.navn = navn;
        this.fødselsdato = fødselsdato;
        this.holdtype = holdtype;
    }

    @Override
    public String toString() {
        return "Medlem{" + "id=" + id + ", navn=" + navn + ", f\u00f8dselsdato=" + fødselsdato + ", holdtype=" + holdtype + '}';
    }
    
    public void addDicipliner(String Diciplin){
        dicipliner.add(Diciplin);
    }
    
    public void removeDicipliner(String Diciplin){
        dicipliner.remove(Diciplin);
    }
    
    public void setDicipliner(ArrayList<String> dici){
        dicipliner = dici;
    }

    public ArrayList<String> getDicipliner() {
        return dicipliner;
    }

    public int getId() {
        return id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getFødselsdato() {
        return fødselsdato;
    }

    public String getHoldtype() {
        return holdtype;
    }

    public void setHoldtype(String holdtype) {
        this.holdtype = holdtype;
    }
    
    
}
