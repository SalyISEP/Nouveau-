package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.repository;

import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.Compteur;
import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteurRepository extends JpaRepository<Compteur, Long> {
    Compteur findByClient(Client client);
}
