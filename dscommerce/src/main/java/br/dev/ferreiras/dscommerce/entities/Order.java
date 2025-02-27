package br.dev.ferreiras.dscommerce.entities;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
  private Instant moment;

  private OrderStatus status;

  @OneToMany(mappedBy = "id.order")
  private Set<OrderItem> items = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "client_id")
  private User client;

  @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
  private Payment payment;

  public Order(Long id, Instant moment, OrderStatus status, Payment payment, User client) {
    this.id = id;
    this.moment = moment;
    this.status = status;
    this.payment = payment;
    this.client = client;
  }

  public Payment getPayment() {
    return payment;
  }

  public void setPayment(Payment payment) {
    this.payment = payment;
  }


  public List<Product> getProducts() {
    return items.stream().map(OrderItem::getProduct).toList();
  }

  public Order(Long id, Instant moment, OrderStatus status, User client) {
    this.id = id;
    this.moment = moment;
    this.status = status;
    this.client = client;
  }

  public Order() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Instant getMoment() {
    return moment;
  }

  public void setMoment(Instant moment) {
    this.moment = moment;
  }

  public OrderStatus getStatus() {
    return status;
  }

  public void setStatus(OrderStatus status) {
    this.status = status;
  }

  public User getClient() {
    return client;
  }

  public void setClient(User client) {
    this.client = client;
  }

  public Set<OrderItem> getItems() {
    return items;
  }

  @Override
  public boolean equals(Object o) {
    if (getClass() != o.getClass()) return false;

    Order order = (Order) o;
    return Objects.equals(id, order.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
