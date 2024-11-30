package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Compteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numCompteur;

    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Client client;

    @OneToMany(mappedBy = "compteur", cascade = CascadeType.ALL)
    private List<TransactionRecord> transactions;

    // Default constructor
    public Compteur() {}

    // Constructor with necessary fields
    public Compteur(String numCompteur, Client client) {
        this.numCompteur = numCompteur;
        this.client = client;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumCompteur() {
        return numCompteur;
    }

    public void setNumCompteur(String numCompteur) {
        this.numCompteur = numCompteur;
    }

    public Client getUtilisateur() {
        return client;
    }

    public void setUtilisateur(Client client) {
        this.client = client;
    }

    public List<TransactionRecord> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionRecord> transactions) {
        this.transactions = transactions;
    }
}
