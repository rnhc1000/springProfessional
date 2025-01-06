package br.dev.ferreiras.crudchallenge.controllers;

import br.dev.ferreiras.crudchallenge.dto.ClientDTO;
import br.dev.ferreiras.crudchallenge.services.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

  private final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @GetMapping
  public ResponseEntity<Page<ClientDTO>> getClients(Pageable pageable) {

    return ResponseEntity.ok(clientService.returnPagedClients(pageable));
  }
}
