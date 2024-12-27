package br.dev.ferreiras.challengeone.services;

import br.dev.ferreiras.challengeone.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShippingService {

  private final Order order;



  public ShippingService(Order order) {
    this.order = order;
  }


  public Double shipment(Order order) {

    Double shippingRateSmallerThanOneHundred = 20.0d;
    Double shippingRateLargerThanOneHundredAndSmallerThanTwoHundred= 12.0d;
    Double shippingRateLargerThanTwoHundred = 0.0d;

//    Map<String, Double> map = Map.ofEntries(
//      entry("simple", 20.0d),
//        entry("prime", 12.0d),
//        entry("premium", 0.0d)
//    );

    if(order.getBasic() < 100.0d) {
      return shippingRateSmallerThanOneHundred;
    } else if (order.getBasic() > 100.00d && order.getBasic() < 200.0d) {
      return shippingRateLargerThanOneHundredAndSmallerThanTwoHundred;
    } else {
      return shippingRateLargerThanTwoHundred;
    }
  }

}
