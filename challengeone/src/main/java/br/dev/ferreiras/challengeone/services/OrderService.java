package br.dev.ferreiras.challengeone.services;

import br.dev.ferreiras.challengeone.entities.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

  private final Order order;
  private final ShippingService shippingService;

  public OrderService(Order order, ShippingService shippingService) {
    this.order = order;
    this.shippingService = shippingService;
  }


  public Double total(Order order) {

    if (order.getBasic() < 100.00d) {
      return order.getBasic() - order.getBasic() * (order.getDiscount() / 100.0d) + shippingService.shipment(order);
    } else if(order.getBasic() > 100.0d && order.getBasic() < 200.0d) {
      return order.getBasic() - order.getBasic() * (order.getDiscount()) / 100.0d + shippingService.shipment(order);
    } else {
      return order.getBasic() - order.getBasic() * (order.getDiscount()) / 100.0d;
    }
  }
}
