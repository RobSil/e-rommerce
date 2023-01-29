package com.robsil.erommerce.data.repository;

import com.robsil.erommerce.data.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
