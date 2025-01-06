package br.dev.ferreiras.crudchallenge.repository;

import br.dev.ferreiras.crudchallenge.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository <Client, Long>{
}
