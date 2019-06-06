package com.example.swimmingtraining;

public class UZapros {

    public String ot;
    public String k;


    // Default constructor required for calls to
// DataSnapshot.getValue(User.class)
    public UZapros() {
    }

    public UZapros(String ot, String k) {
        this.ot = ot;
        this.k = k;
    }

    public String getOt() {
        return ot;
    }
    public String getK() {
        return k;
    }
}

