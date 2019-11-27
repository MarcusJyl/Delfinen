
package delfinen.Model;
/*
@author Marcus Jensen
*/
public class Medlem {

    private int id;
    private String navn;
    private String fødselsdato;
    private String holdtype;

    public Medlem(int id, String navn, String fødselsdato, String holdtype) {
        this.id = id;
        this.navn = navn;
        this.fødselsdato = fødselsdato;
        this.holdtype = holdtype;
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
