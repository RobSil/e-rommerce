package com.robsil.erommerce.data.repository;

import com.robsil.erommerce.data.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
