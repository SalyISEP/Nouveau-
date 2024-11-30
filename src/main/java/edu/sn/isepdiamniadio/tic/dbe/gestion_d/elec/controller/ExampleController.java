package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200") // Autoriser les requêtes depuis Angular
public class ExampleController {

    @GetMapping("/greeting")
    public ResponseEntity<String> getGreeting() {
        return ResponseEntity.ok("Bonjour depuis Spring Boot !");
    }
}
