package br.dev.ferreiras.dscommerce.services;

import br.dev.ferreiras.dscommerce.dto.CategoryDTO;
import br.dev.ferreiras.dscommerce.dto.ProductDTO;
import br.dev.ferreiras.dscommerce.dto.ProductMinDTO;
import br.dev.ferreiras.dscommerce.entities.Category;
import br.dev.ferreiras.dscommerce.entities.Product;
import br.dev.ferreiras.dscommerce.repositories.ProductRepository;
import br.dev.ferreiras.dscommerce.services.exceptions.DatabaseException;
import br.dev.ferreiras.dscommerce.services.exceptions.EntityNotFoundException;
import br.dev.ferreiras.dscommerce.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {


  private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Transactional(readOnly = true)
  public ProductDTO findById(Long id) {

    Product product = productRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Resource Not Found"));

    return new ProductDTO(product);
  }

  @Transactional(readOnly = true)
  public Page<ProductDTO> findAll(String name, Pageable pageable) {

    Page<Product> products = productRepository.searchByName(name, pageable);

    return products.map(ProductDTO::new);
    /*
    25-01-05 Sun 13:39:21.861 WARN  SpringDataJacksonConfiguration$PageModule$WarningLoggingModifier Serializing PageImpl
    instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.

     */
  }

  @Transactional(readOnly = true)
  public Page<ProductDTO> findAllJoin(String name, Pageable pageable) {

    Page<Product> products = productRepository.findProductCategories(name, pageable);

    return products.map(ProductDTO::new);
  }

  @Transactional(readOnly = true)
  public Page<ProductMinDTO> findAllJoinMinDTO(String name, Pageable pageable) {

    Page<Product> products = productRepository.findProductCategories(name, pageable);

    return  products.map(product -> new ProductMinDTO(
        product.getId(), product.getName(), product.getPrice(), product.getImgUrl()
    ));
  }

  @Transactional
  public ProductDTO insert(ProductDTO productDTO) {

    Product product = new Product();
    copyDtoToEntity(productDTO, product);
    product = productRepository.save(product);

    return new ProductDTO(product);
  }

  @Transactional
  public ProductDTO update(Long id, ProductDTO productDTO) {

    try {
      Product product = productRepository.getReferenceById(id);
      copyDtoToEntity(productDTO, product);
      product = productRepository.save(product);
      return new ProductDTO(product);
    }
    catch (RuntimeException exception) {
      throw new EntityNotFoundException("Id non existent!!!");
    }

  }

  @Transactional(propagation = Propagation.SUPPORTS)
  public void delete(Long id) {

    if(!productRepository.existsById(id)) {
      throw new ResourceNotFoundException("Id not found!");
    }

    try {
      logger.info(" PRODUCT ::: ID Exists");
      productRepository.deleteById(id);
    } catch (DataIntegrityViolationException exception) {

      logger.info("PRODUCT ::: Integrity Violation, {}", exception.getMessage());
      throw new DatabaseException("Referential Integrity Violation");
    }
  }
  private void copyDtoToEntity(ProductDTO productDTO, Product entity) {

    entity.setName(productDTO.getName());
    entity.setDescription(productDTO.getDescription());
    entity.setPrice(productDTO.getPrice());
    entity.setImgUrl(productDTO.getImgUrl());
    entity.getCategories().clear();
    for(CategoryDTO categoryDTO : productDTO.getCategories()) {
      Category category = new Category(categoryDTO.getId(), categoryDTO.getName());
      entity.getCategories().add(category);
    }
  }
}
