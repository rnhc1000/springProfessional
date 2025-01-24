package com.devsuperior.dsmeta;

import com.devsuperior.dsmeta.config.MetaData;
import com.devsuperior.dsmeta.config.MetaDataConfig;
import com.devsuperior.dsmeta.dto.SummarySalesDTO;
import com.devsuperior.dsmeta.projections.SummarySales;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.Meta;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
@EnableAspectJAutoProxy
public class DsmetaApplication  implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(DsmetaApplication.class);
	private final SaleRepository saleRepository;

	private final MetaData metaData;

  public DsmetaApplication(SaleRepository saleRepository, MetaData metaData) {
    this.saleRepository = saleRepository;
    this.metaData = metaData;
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

		List<String> props = metaData.metaData();
		logger.info("Properties: {}", props);
	}
}
