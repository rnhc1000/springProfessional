package br.dev.ferreiras.dscommerce.dto;

import br.dev.ferreiras.dscommerce.entities.OrderItem;
import br.dev.ferreiras.dscommerce.entities.OrderStatus;
import br.dev.ferreiras.dscommerce.entities.Order;
import br.dev.ferreiras.dscommerce.entities.Product;
import jakarta.validation.constraints.NotEmpty;


import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDTO {

  private Long id;
  private Instant moment;
  private OrderStatus status;
  private UserMinDTO client;
  private PaymentDTO payment;

  @NotEmpty(message = "Order must have one item, at least!")
  private List<OrderItemDTO> items = new ArrayList<>();

  public OrderDTO() {
  }

  public OrderDTO(Long id, Instant moment, OrderStatus status, UserMinDTO client, PaymentDTO payment) {
    this.id = id;
    this.moment = moment;
    this.status = status;
    this.client = client;
    this.payment = payment;
  }

  public OrderDTO(Order entity) {
    id = entity.getId();
    moment = entity.getMoment();
    status = entity.getStatus();
    client = new UserMinDTO(entity.getId(), entity.getClient().getName());
    payment = (entity.getPayment()) == null ? null : new PaymentDTO(entity.getId(), entity.getMoment());
    for(OrderItem item : entity.getItems()) {
      OrderItemDTO orderItemDTO = new OrderItemDTO(item);
      items.add(orderItemDTO);
    }
  }

  public Long getId() {
    return id;
  }

  public Instant getMoment() {
    return moment;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public UserMinDTO getClient() {
    return client;
  }

  public PaymentDTO getPayment() {
    return payment;
  }

  public List<OrderItemDTO> getItems() {
    return items;
  }

  public Double getTotal() {
    Double sum = 0.0;
    for (OrderItemDTO item : items) {
      sum+=item.getSubTotal();
    }
    return sum;
  }
}
