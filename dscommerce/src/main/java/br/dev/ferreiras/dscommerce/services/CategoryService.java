package br.dev.ferreiras.dscommerce.services;

import br.dev.ferreiras.dscommerce.dto.CategoryDTO;
import br.dev.ferreiras.dscommerce.entities.Category;
import br.dev.ferreiras.dscommerce.repositories.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

  private final CategoryRepository categoryRepository;

  public CategoryService(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @Transactional(readOnly = true)
  public List<CategoryDTO> findAll() {
    List<Category> categories = categoryRepository.findAll();
    List<CategoryDTO> result = new ArrayList<>();
    for(Category category : categories) {
      result.add(new CategoryDTO(category.getId(), category.getName()));
    }

    return result;
  }
}
