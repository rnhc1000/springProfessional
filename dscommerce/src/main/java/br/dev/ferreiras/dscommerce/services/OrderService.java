package br.dev.ferreiras.dscommerce.services;

import br.dev.ferreiras.dscommerce.dto.OrderDTO;
import br.dev.ferreiras.dscommerce.repositories.OrderRepository;
import br.dev.ferreiras.dscommerce.services.exceptions.ResourceNotFoundException;
import br.dev.ferreiras.dscommerce.entities.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

  private final OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Transactional(readOnly = true)
  public OrderDTO findById(Long id) {

    Order order = orderRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));

    return new OrderDTO(order);
  }
}
