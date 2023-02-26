package com.robsil.erommerce.service.impl;

import com.robsil.erommerce.data.domain.Category;
import com.robsil.erommerce.data.repository.CategoryRepository;
import com.robsil.erommerce.model.category.CategoryCreateRequest;
import com.robsil.erommerce.model.category.CategorySaveRequest;
import com.robsil.erommerce.model.exception.EntityNotFoundException;
import com.robsil.erommerce.model.exception.HttpConflictException;
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
        // we have to verify, that category.id doesn't equal any of parent ids.
        Category parent = category.getParent();
        while (parent != null) {
            if (parent.getId().equals(category.getId())) {
                throw new HttpConflictException("Unable to save, there is category root recursion. CategoryID: %s, ParentID: %s"
                                .formatted(category.getId().toString(), parent.getId().toString()));
            }

            parent = parent.getParent();
        }

        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
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
    public List<Category> findAllByParentId(Long parentId) {
        return categoryRepository.findAllByParentId(parentId);
    }

    @Override
    public List<Category> findAllRoots() {
        return findAllByParentId(null);
    }

    @Override
    @Transactional
    public Category create(CategoryCreateRequest request) {

        var parent = findById(request.getParentId());

        var category = Category.builder()
                .title(request.getTitle())
                .parent(parent)
                .build();

        category = saveEntity(category);

        return category;
    }

    @Override
    @Transactional
    public Category save(CategorySaveRequest request) {

        var parent = findById(request.getParentId());

        var category = findById(request.getId());

        category.setParent(parent);
        category.setTitle(request.getTitle());

        category = saveEntity(category);

        return category;
    }
}
