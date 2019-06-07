package com.example.swimmingtraining;

public class UList {

    public String fam;
    public String im;
    public String otch;
    public String uid;


    // Default constructor required for calls to
// DataSnapshot.getValue(User.class)
    public UList() {
    }

    public UList(String fam, String im, String otch, String uid) {
        this.fam = fam;
        this.im = im;
        this.otch = otch;
        this.uid = uid;
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
    public String getUidik() { return uid; }
}
