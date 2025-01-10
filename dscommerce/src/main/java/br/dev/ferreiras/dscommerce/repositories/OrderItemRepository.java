package br.dev.ferreiras.dscommerce.repositories;

import br.dev.ferreiras.dscommerce.entities.OrderItem;
import br.dev.ferreiras.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
