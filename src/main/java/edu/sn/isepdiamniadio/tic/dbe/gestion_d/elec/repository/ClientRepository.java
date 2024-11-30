package edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.repository;

import edu.sn.isepdiamniadio.tic.dbe.gestion_d.elec.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
        Optional<Client> findByEmail(String email);
}
