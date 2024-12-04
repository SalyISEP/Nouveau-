package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Compteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numCompteur;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(mappedBy = "compteur", cascade = CascadeType.ALL)
    private List<TransactionRecord> transactions;


    @ManyToOne
    @JoinColumn(name = "compteur_id")
    private Compteur compteur;

    // Autres getters, setters et constructeurs...
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
