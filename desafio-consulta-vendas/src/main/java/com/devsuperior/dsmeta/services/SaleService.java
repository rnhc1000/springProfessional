package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.ReportSalesDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SummarySalesDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.projections.ReportSales;
import com.devsuperior.dsmeta.projections.SummarySales;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import com.devsuperior.dsmeta.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class SaleService {

  private final SaleRepository repository;

  private static final Logger logger = LoggerFactory.getLogger(SaleService.class);

  public SaleService(SaleRepository repository) {
    this.repository = repository;
  }

  @Transactional(readOnly = true)
  public SaleMinDTO findById(Long id) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("Resource Not Found!!!");
    }
    Sale result = repository.getReferenceById(id);
    return new SaleMinDTO(result);
  }

  @Transactional(readOnly = true)
  public Page<SaleMinDTO> returnAllPaged(Pageable pageable) {
    Page<Sale> sales = repository.findAll(pageable);

    return sales.map(SaleMinDTO::new);
  }

  @Transactional(readOnly = true)
  public List<SummarySalesDTO> returnSalesBySeller(String minDate, String maxDate) {

    logger.info("DATE BEFORE CLEANING ::: Start date: {}, Final date: {}", minDate, maxDate);
    LocalDate[] dates = cleanDate(minDate, maxDate);
    LocalDate start = dates[0];
    LocalDate end = dates[1];

    logger.info("DATE AFTER CLEANED ::: Start date: {}, Final date: {}", start, end);

    List<SummarySales> summary = repository.findSales(start, end);

    return summary.stream().map(SummarySalesDTO::new).toList();
  }

  @Transactional(readOnly = true)
  public Page<ReportSalesDTO> returnPagedReportSales(Pageable pageable, String minDate, String maxDate, String name) {
    if (logger.isInfoEnabled()) {
      logger.info("PAGED REPORT - DATE BEFORE CLEANING ::: Start date: {}, Final date: {}, Name: {}", minDate, maxDate, name);
    }

    LocalDate[] dates = cleanDate(minDate, maxDate);
    LocalDate start = dates[0];
    LocalDate end = dates[1];
    if (logger.isInfoEnabled()) {
      logger.info("PAGED-REPORT - DATE AFTER CLEANED ::: Start date: {}, Final date: {}, Name: {}", start, end, name);
    }

    Page<ReportSales> reports = repository.findReportSales(pageable, start, end, name);

    return reports.map(ReportSalesDTO::new);
  }

  private LocalDate[] cleanDate(String minDate, String maxDate) {
    LocalDate start = null;
    LocalDate end = null;

    if (!minDate.isEmpty()) {
      try {
        start = LocalDate.parse(minDate);
      } catch (DateTimeParseException exception) {
        throw new IllegalArgumentException("Invalid date!");
      }
    }
    if(!maxDate.isEmpty()) {
      try {
        end = LocalDate.parse(maxDate);
      } catch (DateTimeParseException exception) {
        throw new IllegalArgumentException("Invalid date!");
      }
    }
    if (minDate.isEmpty()) {
      start = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()).minusYears(1L);
    }
    if (maxDate.isEmpty())  {
      end = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
    }
    return new LocalDate[]{start, end};
  }
}
