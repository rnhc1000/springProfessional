package br.dev.ferreiras.dscommerce.controllers;

import br.dev.ferreiras.dscommerce.dto.ProductDTO;
import br.dev.ferreiras.dscommerce.entities.Product;
import br.dev.ferreiras.dscommerce.repositories.ProductRepository;
import br.dev.ferreiras.dscommerce.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping(value="/{id}")
  public ProductDTO findById(@PathVariable Long id) {

   return productService.findById(id);
  }

  @GetMapping
  public Page<ProductDTO> findAll(Pageable pageable) {

    return productService.findAll(pageable);
  }
}
