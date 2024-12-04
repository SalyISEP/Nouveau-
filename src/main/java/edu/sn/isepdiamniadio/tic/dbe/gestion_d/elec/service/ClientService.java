package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.service;

import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.Compteur;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.Client;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.TransactionRecord;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.repository.ClientRepository;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.repository.CompteurRepository;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.repository.TransactionRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CompteurRepository compteurRepository;

    @Autowired
    private TransactionRecordRepository transactionRepository;


    private final BCryptPasswordEncoder passwordEncoder;

    public ClientService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
    @Autowired
    public ClientService(ClientRepository clientRepository, BCryptPasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Client register(Client client) {
        // Vérifier si l'email existe déjà
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Un client avec cet email existe déjà !");
        }

        // Hacher le mot de passe
        client.setPassword(passwordEncoder.encode(client.getPassword()));

        // Sauvegarder le client
        return clientRepository.save(client);
    }

    // Achat d'électricité par un utilisateur.
    public String achatElectricite(Long utilisateurId, double montant) {
        Client client = clientRepository.findById(utilisateurId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
        if (client.getBalance() < montant) {
            return "Solde insuffisant pour cet achat.";
        }
        // Déduction du montant et création d'une transaction
        client.setBalance(client.getBalance() - montant);
        double tarifParKWh = 181.82; // Exemple de tarif
        double quantiteEnergie = montant / tarifParKWh;
        String codeWoyofal = "WYO-" + System.currentTimeMillis();
        Compteur compteur = compteurRepository.findByClient(client);
        if (compteur == null) {
            throw new IllegalStateException("Compteur non trouvé pour cet utilisateur.");
        }
        TransactionRecord transaction = new TransactionRecord();
        //transactionRepository.save();
        clientRepository.save(client);
        // Notification
        String notificationMessage = String.format(
                "Votre achat a été effectué avec succès !\n" +
                        "Code Woyofal : %s\n" +
                        "Numéro compteur : %s\n" +
                        "Quantité : %.2f kWh\n" +
                        "Date et heure de l'achat : %s",
                codeWoyofal, compteur.getNumCompteur(), quantiteEnergie, LocalDateTime.now()
        );
        System.out.println(notificationMessage);
        return "Achat d'électricité réussi. Une notification a été envoyée.";
    }

    //Active un utilisateur.
    public boolean activerUtilisateur(Long id) {
        return setUtilisateurActif(id, true);
    }

    //Désactive un utilisateur.
    public boolean desactiverUtilisateur(Long id) {
        return setUtilisateurActif(id, false);
    }

    private boolean setUtilisateurActif(Long id, boolean actif) {
        Client client = clientRepository.findById(id).orElse(null);

        if (client != null && client.isActif() != actif) {
            client.setActif(actif);
            clientRepository.save(client);
            return true;
        }
        return false;
    }

    //Crée un nouvel utilisateur.
    public Client createUtilisateur(Client client) {
        return clientRepository.save(client);
    }

    // Récupère tous les utilisateurs.
    public List<Client> getAllUtilisateurs() {
        return clientRepository.findAll();
    }

    //  Récupère un utilisateur par son ID.
    public Optional<Client> getUtilisateurById(Long utilisateurId) {
        return clientRepository.findById(utilisateurId);
    }

    //Met à jour un utilisateur existant.
    public Client updateUtilisateur(Long id, Client clientDetails) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));

        client.setNom(clientDetails.getNom());
        client.setEmail(clientDetails.getEmail());
        client.setBalance(clientDetails.getBalance());
        client.setPassword(clientDetails.getPassword());

        return clientRepository.save(client);
    }

    //Supprime un utilisateur.
    public void deleteUtilisateur(Long id) {
        clientRepository.deleteById(id);
    }

    //Attribue un numéro unique à un utilisateur.
    public void attribuerNumero(Long utilisateurId) {
        Client client = clientRepository.findById(utilisateurId)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
        String numeroAttribue = "USR-" + utilisateurId;
        client.setNumero(numeroAttribue);
        clientRepository.save(client);
    }

    public void save(Client client) {
        clientRepository.save(client);
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email).orElse(null);
    }



//    public Client getClientByEmail(String email) {
//        return clientRepository.findByEmail(email);
//    }
}


//    //lii sii yokouu
//    @Autowired
//    private ConsulterConsomation consulterConsomation ;
//
//    // Méthode pour récupérer l'historique des consommations d'un client
//    public List<TransactionRecord> consulterHistoriqueConsommation(Long utilisateurId) {
//        Client client = clientRepository.findById(utilisateurId)
//                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé"));
//
//        // Utilise la classe ConsultationConsommation pour obtenir l'historique des consommations
//        return consulterConsomation.getHistoriqueConsommation(client);
//    }
