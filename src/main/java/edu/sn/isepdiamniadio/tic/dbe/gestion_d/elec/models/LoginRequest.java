package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models;

public class LoginRequest {
    private String email;
    private String password;

    // Constructeur
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getter pour l'email
    public String getEmail() {
        return email;
    }

    // Setter pour l'email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter pour le mot de passe
    public String getPassword() {
        return password;
    }

    // Setter pour le mot de passe
    public void setPassword(String password) {
        this.password = password;
    }
}
