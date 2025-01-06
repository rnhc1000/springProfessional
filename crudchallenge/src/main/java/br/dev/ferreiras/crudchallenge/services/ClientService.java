package br.dev.ferreiras.crudchallenge.services;

import br.dev.ferreiras.crudchallenge.dto.ClientDTO;
import br.dev.ferreiras.crudchallenge.entities.Client;
import br.dev.ferreiras.crudchallenge.repository.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

  private final ClientRepository clientRepository;

  public ClientService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  public Page<ClientDTO> returnPagedClients(Pageable pageable) {

    Page<Client> clients =  clientRepository.findAll(pageable);

    return clients.map(ClientDTO::new);
  }
}
