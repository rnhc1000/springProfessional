package com.devsuperior.dsmeta;

import com.devsuperior.dsmeta.dto.SummarySalesDTO;
import com.devsuperior.dsmeta.projections.SummarySales;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class DsmetaApplication  implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(DsmetaApplication.class);
	private final SaleRepository saleRepository;

  public DsmetaApplication(SaleRepository saleRepository) {
    this.saleRepository = saleRepository;
  }

  public static void main(String[] args) {
		SpringApplication.run(DsmetaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		List<SummarySales> list = saleRepository.findSales(LocalDate.parse("2021-01-01"), LocalDate.parse("2021-12-31"));
		List<SummarySalesDTO> result = list.stream().map(SummarySalesDTO::new).toList();
		for( SummarySalesDTO obj : result)  {
			logger.info("SellerName: {}, Total: {}", obj.getSellerName() ,obj.getTotal());
		}
	}
}
