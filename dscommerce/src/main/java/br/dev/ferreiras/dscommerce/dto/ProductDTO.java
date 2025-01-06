package br.dev.ferreiras.dscommerce.dto;

import br.dev.ferreiras.dscommerce.entities.Product;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
/*
ModelMapper - lib to copy attributes of an object to another object
 */

public class ProductDTO{

  private Long id;

  @Size(min = 3, max = 80, message = "Minimum 3, maximum 80 characters")
  @NotBlank(message = "required")
  private String name;

  @Size(min = 10, message = "Minimum of 10 characters!")
  @NotBlank(message = "required")
  private String description;

  @Positive(message = "Price must be positive!")
  private Double price;

  private String imgUrl;

  public ProductDTO() {
  }

  public ProductDTO(Long id, String name, String description, Double price, String imgUrl) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.imgUrl = imgUrl;
  }

  public ProductDTO(Product entity) {
    id = entity.getId();
    name = entity.getName();
    description = entity.getDescription();
    price = entity.getPrice();
    imgUrl = entity.getImgUrl();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Double getPrice() {
    return price;
  }

  public String getDescription() {
    return description;
  }

  public String getImgUrl() {
    return imgUrl;
  }

}
