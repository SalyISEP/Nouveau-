package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.service;

import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.DemandeCompteur;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.repository.DemandeCompteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class DemandeCompteurService {

    @Autowired
    private DemandeCompteurRepository demandeCompteurRepository;  // Repositoire pour l'accès à la base de données

    public boolean processDemande(DemandeCompteur demande) {
        try {
            // Sauvegarder la demande dans la base de données
            demandeCompteurRepository.save(demande);
            return true;
        } catch (Exception e) {
            // En cas d'erreur, retourner false
            return false;
        }
    }



}
