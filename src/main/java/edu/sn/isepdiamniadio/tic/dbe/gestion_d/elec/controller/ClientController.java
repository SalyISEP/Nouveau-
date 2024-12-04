package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.controller;

import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.Client;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    public Client registerClient(@RequestBody Client client) {
        return clientService.saveClient(client);
    }

    @GetMapping("/{email}")
    public Client getClientByEmail(@PathVariable String email) {
        return clientService.getClientByEmail(email);
    }

    @GetMapping("/{id}")
    public String getClientById(@PathVariable Long id) {
        return "Données du client avec l'ID " + id;
    }

    @PostMapping
    public String createClient(@RequestBody String client) {
        return "Client créé : " + client;
    }

}