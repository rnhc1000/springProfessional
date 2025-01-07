package com.devsuperior.dsmeta.services;

import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	private final SaleRepository repository;

  public SaleService(SaleRepository repository) {
    this.repository = repository;
  }

  public SaleMinDTO findById(Long id) {
		if(!repository.existsById(id)) {
			throw new ResourceNotFoundException("Resource Not Found!!!");
		}
		Sale result = repository.getReferenceById(id);
		return new SaleMinDTO(result);
	}

	public Page<SaleMinDTO> returnAllPaged(Pageable pageable) {
		Page<Sale> sales = repository.findAll(pageable);

		return sales.map(SaleMinDTO::new);
	}
}
