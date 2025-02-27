package br.dev.ferreiras.dscommerce.controllers;

import br.dev.ferreiras.dscommerce.dto.CategoryDTO;
import br.dev.ferreiras.dscommerce.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public ResponseEntity<List<CategoryDTO>> findCategories() {

    return ResponseEntity.ok(categoryService.findAll());
  }
}
