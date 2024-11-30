package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;
    private String numCompteur;
    private String password;// Ajout de l'attribut password
    private double balance; // Ajout de l'attribut balance
    private boolean isActive; // Ajout de l'attribut isActive pour l'état actif/inactif
    private boolean active; // Remarque : le champ est nommé "active".

    // Méthode getter pour `active`
    public boolean isActif() {
        return active;
    }

    // Méthode setter pour `active`
    public void setActif(boolean active) {
        this.active = active;
    }

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Paiement> paiements;  // Liste de paiements liés à l'utilisateur


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

}
