package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double montant;
    private String modePaiement;

    @Column(name = "date_paiement")
    private LocalDate datePaiement;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;  // Lien vers l'utilisateur pour chaque paiement

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {

        this.datePaiement = datePaiement;
    }

    public Client getClient() {

        return client;
    }

    public void setClient(Client client) {

        this.client = client;
    }
}
