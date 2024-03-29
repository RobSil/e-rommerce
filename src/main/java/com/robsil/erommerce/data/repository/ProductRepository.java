package com.robsil.erommerce.data.repository;

import com.robsil.erommerce.data.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findBySku(String sku);
    List<Product> findAllByCategoryId(Long categoryId);
    Page<Product> findAllByCategoryId(Long categoryId, Pageable pageable);

    @Modifying
    @Query("delete from Product product where product.category.id = :categoryId")
    void deleteAllByCategoryId(Long categoryId);

}
