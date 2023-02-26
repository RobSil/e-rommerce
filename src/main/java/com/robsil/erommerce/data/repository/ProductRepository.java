package com.robsil.erommerce.data.repository;

import com.robsil.erommerce.data.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findBySku(String sku);
    List<Product> findAllByCategoryId(Long categoryId);

}
