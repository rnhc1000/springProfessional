package br.dev.ferreiras.crudchallenge.repository;

import br.dev.ferreiras.crudchallenge.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
}
