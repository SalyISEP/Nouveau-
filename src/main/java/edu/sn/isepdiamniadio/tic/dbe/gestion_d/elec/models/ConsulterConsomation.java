package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ConsulterConsomation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
