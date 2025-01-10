package br.dev.ferreiras.dscommerce.controllers;

import br.dev.ferreiras.dscommerce.dto.OrderDTO;
import br.dev.ferreiras.dscommerce.dto.ProductDTO;
import br.dev.ferreiras.dscommerce.services.OrderService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

  private final OrderService orderService;

  private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
  @GetMapping(value = "/{id}")
  public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {

    return ResponseEntity.ok(orderService.findById(id));
  }


  @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
  @PostMapping
  public ResponseEntity<OrderDTO> insert(@Valid @RequestBody OrderDTO orderDTO) {

    orderDTO = orderService.insert(orderDTO);
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequestUri()
        .path("/{id}")
        .buildAndExpand(orderDTO.getId()).toUri();
    logger.info("ID new Order, {}", orderDTO.getId());

    return ResponseEntity.created(uri).body(orderDTO);
  }
}
