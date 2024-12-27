package br.dev.ferreiras.challengeone;

import br.dev.ferreiras.challengeone.entities.Order;
import br.dev.ferreiras.challengeone.services.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"br.dev.ferreiras.challengeone"})
public class ChallengeoneApplication implements CommandLineRunner {

	private final OrderService orderService;

	public ChallengeoneApplication( OrderService orderService) {
		this.orderService = orderService;
	}

	public static void main(String[] args) {

		SpringApplication.run(ChallengeoneApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		int newOrder = 1034;
		double total =150.0d;
		double discount = 20.0d;
		Order order = new Order(newOrder,total,discount);

		System.out.println("Pedido codigo " +  order.getCode());
		System.out.printf("Valor Total: R$ %.2f" , orderService.total(order));
	}
}
