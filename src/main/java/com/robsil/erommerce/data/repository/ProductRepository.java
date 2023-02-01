package com.robsil.erommerce.data.repository;

import com.robsil.erommerce.data.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {

    Optional<Product> findBySku(String sku);
    List<Product> findAllByCategoryId(String categoryId);

}
