package com.robsil.erommerce.controller;

import com.robsil.erommerce.data.domain.Category;
import com.robsil.erommerce.model.category.CategoryCreateRequest;
import com.robsil.erommerce.model.category.CategoryDto;
import com.robsil.erommerce.model.category.CategorySaveRequest;
import com.robsil.erommerce.service.CategoryService;
import com.robsil.erommerce.service.impl.CategoryDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryDtoMapper categoryDtoMapper;

    @GetMapping
    ResponseEntity<List<CategoryDto>> getAll() {
        return new ResponseEntity<>(categoryService.findAll().stream().map(categoryDtoMapper).toList(), HttpStatus.OK);
    }

//    @GetMapping("/{categoryId}/products")
//    ResponseEntity<List<ProductDto>> getAllProductsByCategoryId(@PathVariable String categoryId) {}

    @PostMapping
    ResponseEntity<CategoryDto> create(@RequestBody @Valid CategoryCreateRequest req) {
        return new ResponseEntity<>(categoryDtoMapper.apply(categoryService.create(req)), HttpStatus.CREATED);
    }

    @PutMapping
    ResponseEntity<CategoryDto> save(@RequestBody @Valid CategorySaveRequest req) {
        return new ResponseEntity<>(categoryDtoMapper.apply(categoryService.save(req)), HttpStatus.OK);
    }

}
