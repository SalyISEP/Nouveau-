package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.service;

import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.Client;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class AdministrateurService {

    @Autowired
    private ClientRepository clientRepository;

    // Activer un utilisateur
    public void activerUtilisateur(Long utilisateurId) {
        Client client = clientRepository.findById(utilisateurId).orElse(null);
        if (client != null) {
            client.setActive(true);
            clientRepository.save(client);
        }
    }

    // Désactiver un utilisateur
    public void desactiverUtilisateur(Long utilisateurId) {
        Client client = clientRepository.findById(utilisateurId).orElse(null);
        if (client != null) {
            client.setActive(false);
            clientRepository.save(client);
        }
    }
    // Obtenir tous les utilisateurs
    public List<Client> obtenirTousLesUtilisateurs() {
        return clientRepository.findAll();
    }


    //New mosii yokouuu
    //pour générer un identifiant unique.
    // Le préfixe "CMP-" est ajouté pour indiquer qu'il s'agit d'un numéro de compteur.
    // Méthode pour attribuer un numéro de compteur à un client
    public void attribuerNumeroCompteur(Long clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            // Générer un numéro de compteur unique
            String numeroCompteur = "CMP-" + UUID.randomUUID().toString();
            client.setNumeroCompteur(numeroCompteur);  // Attribuer le numéro de compteur unique
            clientRepository.save(client);  // Sauvegarder le client avec le numéro de compteur
        }
    }


}
