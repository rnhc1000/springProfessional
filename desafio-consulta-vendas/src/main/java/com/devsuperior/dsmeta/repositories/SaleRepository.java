package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.SummarySales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

  @Query(nativeQuery = true,
      value =
          """
                SELECT t.name AS sellerName, SUM(s.amount) as total FROM tb_sales s INNER JOIN tb_seller t
                ON s.seller_id = t.id WHERE s.date BETWEEN ?1 AND ?2 GROUP BY t.name;
              """)
  List<SummarySales> findSales(LocalDate minDate, LocalDate maxDate);
}
