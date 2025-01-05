package br.dev.ferreiras.dscommerce.services;

import br.dev.ferreiras.dscommerce.dto.ProductDTO;
import br.dev.ferreiras.dscommerce.entities.Product;
import br.dev.ferreiras.dscommerce.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

  private final ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Transactional(readOnly = true)
  public ProductDTO findById(Long id) {

    Optional<Product> result = productRepository.findById(id);
    Product product = result.get();

    return new ProductDTO(product);
  }

  @Transactional(readOnly = true)
  public Page<ProductDTO> findAll(Pageable pageable) {

    Page<Product> products = productRepository.findAll(pageable);

    return products.map(ProductDTO::new);
    /*
    25-01-05 Sun 13:39:21.861 WARN  SpringDataJacksonConfiguration$PageModule$WarningLoggingModifier Serializing PageImpl
    instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
	For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
	or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.

     */
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

    Product product = productRepository.getReferenceById(id);
    copyDtoToEntity(productDTO, product);
    product = productRepository.save(product);

    return new ProductDTO(product);
  }

  private void copyDtoToEntity(ProductDTO productDTO, Product entity) {

    entity.setName(productDTO.getName());
    entity.setDescription(productDTO.getDescription());
    entity.setPrice(productDTO.getPrice());
    entity.setImgUrl(productDTO.getImgUrl());
  }
}
