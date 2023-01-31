package com.robsil.erommerce.service.impl;

import com.robsil.erommerce.data.domain.Category;
import com.robsil.erommerce.data.repository.CategoryRepository;
import com.robsil.erommerce.model.category.CategoryCreateRequest;
import com.robsil.erommerce.model.category.CategorySaveRequest;
import com.robsil.erommerce.model.exception.EntityNotFoundException;
import com.robsil.erommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public Category saveEntity(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(String id) {
        return categoryRepository.findById(id).orElseThrow(() -> {
            log.info("findById: category can't be found. ID: %s".formatted(id));
            return new EntityNotFoundException("Category not found");
        });
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findAllByParentId(String parentId) {
        return categoryRepository.findAllByParentId(parentId);
    }

    @Override
    public List<Category> getAllRoots() {
        return findAllByParentId(null);
    }

    @Override
    @Transactional
    public Category create(CategoryCreateRequest request) {

        var category = Category.builder()
                .title(request.getTitle())
                .parentId(request.getParentId())
                .build();

        category = saveEntity(category);

        return category;
    }

    @Override
    @Transactional
    public Category save(CategorySaveRequest request) {

        var category = findById(request.getId());

        category.setParentId(request.getParentId());
        category.setTitle(request.getTitle());

        category = saveEntity(category);

        return category;
    }
}
