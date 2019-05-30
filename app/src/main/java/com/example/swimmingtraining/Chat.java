package com.example.swimmingtraining;

public class Chat {

    public String sms;
    public String ot;
    public String k;


    // Default constructor required for calls to
// DataSnapshot.getValue(User.class)
    public Chat() {
    }

    public Chat(String sms, String ot, String k) {
        this.sms = sms;
        this.ot = ot;
        this.k=k;
    }

    public String getSms() {
        return sms;
    }
    public String getOt() {
        return ot;
    }
    public String getK() {
        return k;
    }
}