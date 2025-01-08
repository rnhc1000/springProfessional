package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.ReportSalesDTO;
import com.devsuperior.dsmeta.dto.SummarySalesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	private final SaleService service;

  public SaleController(SaleService service) {
    this.service = service;
  }

  @GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/report")
	public ResponseEntity<Page<ReportSalesDTO>> getReport(
			Pageable pageable,
			@RequestParam(name = "minDate", defaultValue = "") final String minDate,
			@RequestParam(name = "maxDate", defaultValue = "") final String maxDate,
			@RequestParam(name = "name",    defaultValue = "") final String name) {

		return ResponseEntity.ok(service.returnPagedReportSales(pageable,minDate, maxDate, name));
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SummarySalesDTO>> getSummary(
			@RequestParam(defaultValue = "") final String minDate,
			@RequestParam(defaultValue = "") final String maxDate
	) {

		return ResponseEntity.ok(service.returnSalesBySeller(minDate, maxDate));
	}

	@GetMapping
	public ResponseEntity<Page<SaleMinDTO>> getAllPaged(Pageable pageable) {

		return ResponseEntity.ok(service.returnAllPaged(pageable));
	}
}
