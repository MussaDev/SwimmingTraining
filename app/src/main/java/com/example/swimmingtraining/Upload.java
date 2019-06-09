package com.example.swimmingtraining;

public class Upload {

    public String name;
    public String famailia;
    public String otchestvo;
    public String dr;
    public String login;
    public String email;
    public String rol;
    public String raiting;
    public String stuge;
    public String about;

    // Default constructor required for calls to
// DataSnapshot.getValue(User.class)
    public Upload() {
    }

    public Upload(String name, String famailia, String otchestvo, String dr, String login, String email, String rol,
                  String raiting, String stuge, String about) {
        this.name = name;
        this.famailia = famailia;
        this.otchestvo=otchestvo;
        this.dr=dr;
        this.login = login;
        this.email = email;
        this.rol = rol;
        this.raiting = raiting;
        this.stuge = stuge;
        this.about = about;
    }

    public String getName() {
        return name;
    }
    public String getfamailia() {
        return famailia;
    }
    public String getotchestvo() {
        return otchestvo;
    }
    public String getEmail() {
        return email;
    }
    public String getdr() {
        return dr;
    }
    public String getlogin() { return login; }
    public String getRaiting() { return raiting; }
    public String getStuge() { return stuge; }
    public String getAbout() { return about; }
}

