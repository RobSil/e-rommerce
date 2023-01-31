package com.robsil.erommerce.data.repository;

import com.robsil.erommerce.data.domain.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, String> {

    List<Category> findAllByParentId(String parentId);

}
