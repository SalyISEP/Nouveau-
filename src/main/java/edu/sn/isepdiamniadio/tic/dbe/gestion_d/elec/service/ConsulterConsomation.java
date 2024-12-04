//package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.service;
//
//import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.Client;
//import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.TransactionRecord;
//import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.repository.TransactionRecordRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ConsulterConsomation {
//
//    @Autowired
//    private TransactionRecordRepository transactionRepository;
//
//    //Récupère l'historique des consommations d'un client.
//    public List<TransactionRecord> getHistoriqueConsommation(Client client) {
//        // Récupère toutes les transactions associées au client
//        List<TransactionRecord> historique = transactionRepository.findByClient(client);
//
//        if (historique.isEmpty()) {
//            throw new IllegalStateException("Aucune transaction trouvée pour cet utilisateur.");
//        }
//
//        return historique;
//    }
//}
