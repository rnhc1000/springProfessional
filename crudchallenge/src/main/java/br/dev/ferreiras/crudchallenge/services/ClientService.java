package br.dev.ferreiras.crudchallenge.services;

import br.dev.ferreiras.crudchallenge.dto.ClientDTO;
import br.dev.ferreiras.crudchallenge.entities.Client;
import br.dev.ferreiras.crudchallenge.repository.ClientRepository;
import br.dev.ferreiras.crudchallenge.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

  private final ClientRepository clientRepository;

  private static final String RESOURCE_NOT_FOUND = "Resource not found!!!";

  private static final Logger logger = LoggerFactory.getLogger(ClientService.class);


  public ClientService(ClientRepository clientRepository) {
    this.clientRepository = clientRepository;
  }

  @Transactional(readOnly = true)
  public Page<ClientDTO> returnPagedClients(Pageable pageable) {

    Page<Client> clients = clientRepository.findAll(pageable);

    return clients.map(ClientDTO::new);
  }

  @Transactional(readOnly = true)
  public ClientDTO returnClientById(Long id) {

    if (!clientRepository.existsById(id)) {
      throw new ResourceNotFoundException(RESOURCE_NOT_FOUND);
    }
    Client client = clientRepository.getReferenceById(id);

    return new ClientDTO(client);
  }

  @Transactional
  public ClientDTO addNewClient(ClientDTO clientDTO) {

    Client client = new Client();
    copyDtoToEntity(clientDTO, client);

    logger.info("NEW CLIENT NAME ::: {}", clientDTO.getName());
    client = clientRepository.save(client);

    Long clientId = client.getId();
    logger.info("NEW CLIENT ID ::: {}", clientId);

    return new ClientDTO(client);
  }

  @Transactional
  public ClientDTO updateClient(Long id, ClientDTO clientDTO) {

    if (!clientRepository.existsById(id)) {
      throw new ResourceNotFoundException(RESOURCE_NOT_FOUND);
    }
    Client client = clientRepository.getReferenceById(id);
    copyDtoToEntity(clientDTO, client);
    client = clientRepository.save(client);
    logger.info("UPDATED CLIENT ID, {}", client.getId());

    return new ClientDTO(client);
  }

  @Transactional
  public void deleteClient(Long id) {
    if(!clientRepository.existsById(id)) {
      throw new ResourceNotFoundException(RESOURCE_NOT_FOUND);
    }
     clientRepository.deleteById(id);
  }
  private void copyDtoToEntity(ClientDTO clientDTO, Client client) {

    client.setName(clientDTO.getName());
    client.setCpf(clientDTO.getCpf());
    client.setIncome(clientDTO.getIncome());
    client.setBirthDate(clientDTO.getBirthDate());
    client.setChildren(clientDTO.getChildren());
  }

}
