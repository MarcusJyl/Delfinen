package delfinen.Util;

public class Item {
    String navn;
    boolean status;
    String holdtype;
    String status1;
    int kontingent;
    int kontingentStatus;

    public Item(String navn, boolean status) {
        this.navn = navn;
        this.status = status;
    }

    public Item(String holdtype, String status1, int kontingent, int kontingentStatus) {
        this.holdtype = holdtype;
        this.status1 = status1;
        this.kontingent = kontingent;
        this.kontingentStatus = kontingentStatus;
    }

    public Item() {
    }

    public void setHoldtype(String holdtype) {
        this.holdtype = holdtype;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public void setKontingent(int kontingent) {
        this.kontingent = kontingent;
    }

    public void setKontingentStatus(int kontingentStatus) {
        this.kontingentStatus = kontingentStatus;
    }


    public String getNavn() {
        return navn;
    }

    public boolean isStatus() {
        return status;
    }

    public String getHoldtype() {
        return holdtype;
    }

    public String getStatus1() {
        return status1;
    }

    public int getKontingent() {
        return kontingent;
    }

    public int getKontingentStatus() {
        return kontingentStatus;
    }
    
    

    @Override
    public String toString() {
        if(status)
            return navn + " har betalt";
        else
            return navn + " har ikke betalt";
    }
    
    
}

