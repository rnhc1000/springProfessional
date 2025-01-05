package br.dev.ferreiras.dscommerce.controllers;

import br.dev.ferreiras.dscommerce.dto.ProductDTO;
import br.dev.ferreiras.dscommerce.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

  private final ProductService productService;

  private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping(value="/{id}")
  public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {

   return ResponseEntity.ok(productService.findById(id));
  }

  @GetMapping
  public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {

    return ResponseEntity.ok(productService.findAll(pageable));
  }

  @PostMapping
  public ResponseEntity<ProductDTO> insert(@RequestBody ProductDTO productDTO) {

    productDTO = productService.insert(productDTO);
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequestUri()
        .path("/{id}")
        .buildAndExpand(productDTO.getId()).toUri();

    logger.info("ID new Product, {}", productDTO.getId());
    return ResponseEntity.created(uri).body(productDTO);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {

    productDTO = productService.update(id, productDTO);
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequestUri()
        .path("/{id}")
        .buildAndExpand(productDTO.getId()).toUri();
    logger.info("Name updated Product, {}", productDTO.getName());

    return ResponseEntity.created(uri).body(productDTO);
  }
}
