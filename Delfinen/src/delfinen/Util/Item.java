package delfinen.Util;

public class Item {
    String navn;
    boolean status;

    public Item(String navn, boolean status) {
        this.navn = navn;
        this.status = status;
    }

    public String getNavn() {
        return navn;
    }

    public boolean isStatus() {
        return status;
    }

    @Override
    public String toString() {
        if(status)
            return navn + " har betalt";
        else
            return navn + " har ikke betalt";
    }
    
    
}

