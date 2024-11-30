package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.service;

import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.Compteur;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.repository.CompteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteurService {

    @Autowired
    private CompteurRepository compteurRepository;

    // Enregistrer un nouveau compteur
    public Compteur creerCompteur(Compteur compteur) {
        return compteurRepository.save(compteur);
    }

    // Obtenir un compteur par ID
    public Compteur obtenirCompteurParId(Long id) {
        return compteurRepository.findById(id).orElse(null);
    }

    // Mettre à jour les informations d'un compteur
    public Compteur mettreAJourCompteur(Long id, Compteur compteur) {
        if (compteurRepository.existsById(id)) {
            compteur.setId(id);
            return compteurRepository.save(compteur);
        }
        return null;
    }

    // Supprimer un compteur par ID
    public void supprimerCompteur(Long id) {
        compteurRepository.deleteById(id);
    }

    // Obtenir tous les compteurs
    public List<Compteur> obtenirTousLesCompteurs() {
        return compteurRepository.findAll();
    }
}
