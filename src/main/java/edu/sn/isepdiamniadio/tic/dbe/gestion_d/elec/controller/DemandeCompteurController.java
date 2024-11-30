package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.controller;

import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.DemandeCompteur;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.service.AdministrateurService;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.service.DemandeCompteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DemandeCompteurController {

    @Autowired
    private DemandeCompteurService demandeCompteurService;  // Un service pour gérer la logique métier

    @Autowired
    public DemandeCompteurController(DemandeCompteurService demandeCompteurService) {
        this.demandeCompteurService = demandeCompteurService;
    }

    @PostMapping("/demandeCompteur")
    public ResponseEntity<String> demandeCompteur(@RequestBody DemandeCompteur demande) {
        // 1. Validation des données reçues
        if (demande.getClient() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Client requis");
        }
        if (demande.getAdresse() == null || demande.getAdresse().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Adresse requise");
        }
        if (demande.getTypeCompteur() == null || demande.getTypeCompteur().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Type de compteur requis");
        }
        // Traitement de la demande (par exemple, enregistrement en base de données)
        boolean isSaved = demandeCompteurService.processDemande(demande);

        // 3. Retour d'une réponse
        if (isSaved) {
            return ResponseEntity.ok("Demande traitée avec succès");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du traitement de la demande");
        }
    }

    //New mosii yokouuu
    @Autowired
    private AdministrateurService administrateurService;

    // Endpoint pour attribuer un numéro de compteur
    @PostMapping("/{clientId}/attribuer-compteur")
    public void attribuerNumeroCompteur(@PathVariable Long clientId) {
        administrateurService.attribuerNumeroCompteur(clientId);
    }
}
