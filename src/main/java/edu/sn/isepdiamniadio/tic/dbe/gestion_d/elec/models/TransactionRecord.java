package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "Transaction")
@Setter
@Getter
@AllArgsConstructor
@ToString
public class TransactionRecord {

    @Id
    @SequenceGenerator(name = "SEQ_TRANSACTION", sequenceName = "SEQ_TRANSACTION", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRANSACTION")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "montant")
    private int montant;
    @Column(name = "quantite")
    private double quantite;
    @Column(name = "codeWoyofal")
    private String codeWoyofal;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "compteur_id")
    private Compteur compteur;// Si le compteur est un identifiant simple, laisse comme String. Sinon,  une relation @ManyToOne ou @OneToOne

    @Column(name = "numéroCompteur")
    private String numéroCompteur;

    @Column(name = "date_operation")
    private LocalDate dateTransaction;


    //@JoinColumn(name = "compteur_id")
    // private Compteur compteur;
    // Constructeurs, getters et setters
    public TransactionRecord(int montant, LocalDate date, String compteur) {
        this.montant = montant;
        this.date = date;
        //this.compteur = compteur;
    }

    public TransactionRecord() {
    }

//    public double getAmount() {
//    }

}