package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.repository;

import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.DemandeCompteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DemandeCompteurRepository extends JpaRepository<DemandeCompteur, Long> {
    //List<DemandeCompteur> findByUtilisateurId(Long utilisateurId);
}