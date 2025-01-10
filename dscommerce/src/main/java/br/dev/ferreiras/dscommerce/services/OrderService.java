package br.dev.ferreiras.dscommerce.services;

import br.dev.ferreiras.dscommerce.dto.OrderDTO;
import br.dev.ferreiras.dscommerce.dto.OrderItemDTO;
import br.dev.ferreiras.dscommerce.entities.*;
import br.dev.ferreiras.dscommerce.repositories.OrderItemRepository;
import br.dev.ferreiras.dscommerce.repositories.OrderRepository;
import br.dev.ferreiras.dscommerce.repositories.ProductRepository;
import br.dev.ferreiras.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

  private final OrderRepository orderRepository;

  private final UserService userService;

  private final ProductRepository productRepository;

  private final OrderItemRepository orderItemRepository;

  private final AuthService authService;

  public OrderService(OrderRepository orderRepository, UserService userService, ProductRepository productRepository, OrderItemRepository orderItemRepository, AuthService authService) {
    this.orderRepository = orderRepository;
    this.userService = userService;
    this.productRepository = productRepository;
    this.orderItemRepository = orderItemRepository;
    this.authService = authService;
  }

  @Transactional(readOnly = true)
  public OrderDTO findById(Long id) {

    Order order = orderRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));

    authService.validateSelfOrAdmin(order.getClient().getId());

    return new OrderDTO(order);
  }

  @Transactional
  public OrderDTO insert( OrderDTO orderDTO) {
    Order order = new Order();
    order.setMoment(Instant.now());
    order.setStatus(OrderStatus.WAITING_PAYMENT);
    User  user = userService.getAuthenticatedUser();
    order.setClient(user);

    for (OrderItemDTO itemDTO : orderDTO.getItems()) {
      Product product = productRepository.getReferenceById(itemDTO.getProductId());
      OrderItem item = new OrderItem(order, product, itemDTO.getQuantity(), product.getPrice());
      order.getItems().add(item);
    }

    orderRepository.save(order);
    orderItemRepository.saveAll(order.getItems());

    return new OrderDTO(order);
  }
}
