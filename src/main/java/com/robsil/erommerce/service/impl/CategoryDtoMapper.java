package com.robsil.erommerce.service.impl;

import com.robsil.erommerce.data.domain.Category;
import com.robsil.erommerce.model.category.CategoryDto;
import com.robsil.erommerce.model.exception.EntityNotFoundException;
import com.robsil.erommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class CategoryDtoMapper implements Function<Category, CategoryDto> {

    private final CategoryService categoryService;

    @Override
    public CategoryDto apply(Category category) {

        if (category == null) {
            throw new IllegalArgumentException("Category can't be null");
        }

        Category parent = null;

        try {
            if (category.getParentId() != null) {
                parent = categoryService.findById(category.getParentId());
            }
        } catch (EntityNotFoundException ignored) {}

        var result = CategoryDto.builder()
                .id(category.getId())
                .title(category.getTitle())
                .parent(null)
                .build();

        if (parent != null) {
            result.setParent(this.apply(category));
        }

        return result;
    }
}
