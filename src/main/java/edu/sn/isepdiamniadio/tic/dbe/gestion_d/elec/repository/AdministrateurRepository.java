package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.repository;

import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrateurRepository extends JpaRepository<Administrateur, Long> {
}
