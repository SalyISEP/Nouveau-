package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.controller;

import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.AuthResponse;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.Client;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.LoginRequest;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.service.AuthService;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private  ClientService clientService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @Autowired
    public AuthController(ClientService clientService, AuthService authService) {
        this.clientService = clientService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Client client) {
        try {
            clientService.register(client); // Utilisez le service d'enregistrement
            return ResponseEntity.status(HttpStatus.CREATED).body(client);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
