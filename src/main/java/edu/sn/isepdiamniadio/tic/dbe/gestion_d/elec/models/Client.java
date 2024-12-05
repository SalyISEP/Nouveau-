package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;
    private String numCompteur;
    private String password; // Ajout de l'attribut password
    private double balance;  // Ajout de l'attribut balance
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "compteur_id")
    private Compteur compteur;

//    @OneToMany(mappedBy = "client") //, cascade = CascadeType.ALL
//    private List<Paiement> paiements;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Paiement> paiements;

    public Client(String nom, String email, String numCompteur, String password, double balance, boolean isActive) {
        this.nom = nom;
        this.email = email;
        this.numCompteur = numCompteur;
        this.password = password;
        this.balance = balance;
        this.isActive = isActive;
    }

    // Getters et setters...
    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setisActive(boolean b) {
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setNumero(String numeroAttribue) {
    }

    public String getNumeroCompteur() {
        return numCompteur;
    }

    public void setNumeroCompteur(String numeroCompteur) {
        this.numCompteur = numeroCompteur;
    }

    // Implémentation de isActif()
    public boolean isActif() {
        return isActive;
    }

    // Implémentation de setActif()
    public void setActif(boolean actif) {
        this.isActive = actif;
    }
}
