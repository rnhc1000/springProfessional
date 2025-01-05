package br.dev.ferreiras.dscommerce.controllers;

import br.dev.ferreiras.dscommerce.dto.ProductDTO;
import br.dev.ferreiras.dscommerce.entities.Product;
import br.dev.ferreiras.dscommerce.repositories.ProductRepository;
import br.dev.ferreiras.dscommerce.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
}
