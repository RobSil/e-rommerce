package com.robsil.erommerce.service;

import com.robsil.erommerce.data.domain.Category;
import com.robsil.erommerce.model.category.CategoryCreateRequest;
import com.robsil.erommerce.model.category.CategorySaveRequest;

import java.util.List;

public interface CategoryService {

    Category findById(String id);

    List<Category> findAll();

    Category create(CategoryCreateRequest request);

    Category save(CategorySaveRequest request);

}
