package com.robsil.erommerce.service;

import com.robsil.erommerce.data.domain.Category;
import com.robsil.erommerce.model.category.CategoryCreateRequest;
import com.robsil.erommerce.model.category.CategorySaveRequest;

import java.util.List;

public interface CategoryService {

    Category findById(Long id);
    List<Category> findAll();
    List<Category> findAllByParentId(Long parentId);
    List<Category> findAllRoots();
    Category create(CategoryCreateRequest request);
    Category save(CategorySaveRequest request);

}
