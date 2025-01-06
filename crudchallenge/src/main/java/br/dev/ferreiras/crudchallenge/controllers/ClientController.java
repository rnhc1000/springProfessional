package br.dev.ferreiras.crudchallenge.controllers;

import br.dev.ferreiras.crudchallenge.dto.ClientDTO;
import br.dev.ferreiras.crudchallenge.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

  @GetMapping(value = "/{id}")
  public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id) {

    return ResponseEntity.ok(clientService.returnClientById(id));
  }

  @PostMapping
  public ResponseEntity<ClientDTO> addNewClient(@Valid @RequestBody ClientDTO clientDTO) {

    clientDTO = clientService.addNewClient(clientDTO);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
        .path("/{id}")
        .buildAndExpand(clientDTO.getId())
        .toUri();

    return ResponseEntity.created(uri).body(clientDTO);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {

    clientDTO = clientService.updateClient(id, clientDTO);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
        .path("/{id}")
        .buildAndExpand(clientDTO.getId())
        .toUri();

    return ResponseEntity.created(uri).body(clientDTO);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> deleteClient(@PathVariable Long id) {

    clientService.deleteClient(id);

    return ResponseEntity.noContent().build();
  }
}
