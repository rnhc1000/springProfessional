package br.dev.ferreiras.dscommerce.repositories;

import br.dev.ferreiras.dscommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query("SELECT obj FROM Product obj WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', ?1, '%'))")
  Page<Product> searchByName(String name, Pageable pageable);

  @Query("SELECT obj FROM Product obj JOIN FETCH obj.categories")
  Page<Product> findProductCategories(String name, Pageable pageable);
}
