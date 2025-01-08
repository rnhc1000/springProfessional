package br.dev.ferreiras.dscommerce.dto;

import br.dev.ferreiras.dscommerce.entities.Category;

public class CategoryDTO {

  private Long id;
  private String name;

  public CategoryDTO() {
  }

  public CategoryDTO(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public CategoryDTO(Category category) {
    id = category.getId();
    name = category.getName();
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }
}
