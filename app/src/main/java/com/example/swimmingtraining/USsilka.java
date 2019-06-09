package com.example.swimmingtraining;

public class USsilka {

    public String ot;
    public String k;
    public String url;


    // Default constructor required for calls to
// DataSnapshot.getValue(User.class)
    public USsilka() {
    }

    public USsilka(String ot, String k, String url) {
        this.ot = ot;
        this.k = k;
        this.url = url;
    }

    public String getOt() {
        return ot;
    }
    public String getK() {
        return k;
    }
    public String getUrl() {
        return url;
    }
}

