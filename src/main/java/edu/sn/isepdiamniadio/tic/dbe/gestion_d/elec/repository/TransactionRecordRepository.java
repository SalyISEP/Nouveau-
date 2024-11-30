package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.repository;

import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.TransactionRecord; // Import correct
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRecordRepository extends JpaRepository<TransactionRecord, Long> {
    // Méthodes personnalisées ici, si nécessaire
}
