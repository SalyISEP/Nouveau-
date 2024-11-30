package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
//@Table(name = "demande_compteur")  // Facultatif si le nom de la table est le même que le nom de la classe
public class DemandeCompteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Client client;

    private String typeCompteur;
    private String description;
    private Date dateDemande;
    private String adresse;

}