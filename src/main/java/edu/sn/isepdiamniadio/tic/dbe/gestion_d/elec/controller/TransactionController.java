package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.controller;

import com.nimbusds.jose.proc.SecurityContext;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.AuthFilter;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.TransactionRecord;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.service.ClientService;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.service.TransactionService;
import jakarta.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final TransactionService transactionService;

    private final ClientService clientService;

    private static final Logger logger = LoggerFactory.getLogger(AuthFilter.class);

    public TransactionController(TransactionService transactionService, ClientService clientService) {
        this.transactionService = transactionService;
        this.clientService = clientService;
    }

    @PostMapping("/transaction/save-transaction")
    public ResponseEntity<String> saveProduit(@RequestBody TransactionRecord transaction) throws Exception
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("produit dto on test = " + transaction.toString());
        String currentUser = authentication.getName();
        logger.info("Achat d'electricite pour l'utilisateur "+currentUser);
        String response = clientService.achatElectricite(currentUser, transaction.getMontant());
        return ResponseEntity.ok(response);
    }
}
