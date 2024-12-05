package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.service;

import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.Client;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.JwtUtil;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final ClientRepository clientRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(
            AuthenticationManager authenticationManager,
             JwtUtil jwtUtil,
            ClientRepository clientRepository,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Méthode d'inscription
    public String register(Client client) {
        // Vérifier si l'utilisateur existe déjà
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }

        // Hasher le mot de passe
        client.setPassword(passwordEncoder.encode(client.getPassword()));

        // Sauvegarder le client
        clientRepository.save(client);

        return "Inscription réussie !";
    }

    // Méthode d'authentification
    public String authenticate(String email, String password) {
        try {
            // Authentification via AuthenticationManager
            /*authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

             */

            // Récupérer les détails du client
            Client client = clientRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

            // Générer le JWT
            return jwtUtil.generateToken(client.getEmail());
        } catch (Exception e) {
            throw new RuntimeException("Email ou mot de passe incorrect", e);
        }
    }
}
