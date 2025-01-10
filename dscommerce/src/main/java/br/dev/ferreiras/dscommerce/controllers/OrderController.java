package br.dev.ferreiras.dscommerce.controllers;

import br.dev.ferreiras.dscommerce.dto.OrderDTO;
import br.dev.ferreiras.dscommerce.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

  private final OrderService orderService;

  private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

  public OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<OrderDTO> findById(@PathVariable Long id) {

    return ResponseEntity.ok(orderService.findById(id));
  }

}
