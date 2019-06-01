package com.example.swimmingtraining;

public class UList {

    public String fam;
    public String im;
    public String otch;


    // Default constructor required for calls to
// DataSnapshot.getValue(User.class)
    public UList() {
    }

    public UList(String fam, String im, String otch) {
        this.fam = fam;
        this.im = im;
        this.otch=otch;
    }

    public String getFam() {
        return fam;
    }
    public String getIm() {
        return im;
    }
    public String getOtch() {
        return otch;
    }
}
