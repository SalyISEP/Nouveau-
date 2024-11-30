package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.controller;

import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.service.TransactionService;
import jakarta.transaction.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction/save-transaction")
    public ResponseEntity<String> saveProduit(@RequestBody Transaction transaction) throws Exception
    {
        System.out.println("produit dto on test = " + transaction.toString());
        String response = transactionService.toString();
        return ResponseEntity.ok(response);
    }
}
