package com.robsil.erommerce.controller;

import com.robsil.erommerce.model.category.CategoryCreateRequest;
import com.robsil.erommerce.model.category.CategoryDto;
import com.robsil.erommerce.model.category.CategorySaveRequest;
import com.robsil.erommerce.model.product.ProductDto;
import com.robsil.erommerce.service.CategoryService;
import com.robsil.erommerce.service.ProductService;
import com.robsil.erommerce.service.dtoMapper.CategoryDtoMapper;
import com.robsil.erommerce.service.dtoMapper.ProductDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final CategoryDtoMapper categoryDtoMapper;
    private final ProductDtoMapper productDtoMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        return new ResponseEntity<>(categoryService.findAll().stream().map(categoryDtoMapper).toList(), HttpStatus.OK);
    }

    // TODO: 31.01.2023 make pageable
    @GetMapping("/roots")
    public ResponseEntity<List<CategoryDto>> getAllRoots() {
        return new ResponseEntity<>(categoryService.getAllRoots().stream().map(categoryDtoMapper).toList(), HttpStatus.OK);
    }

    // TODO: 31.01.2023 make pageable
    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<ProductDto>> getAllProductsByCategoryId(@PathVariable String categoryId) {
        return new ResponseEntity<>(productService.findAllByCategoryId(categoryId).stream().map(productDtoMapper).toList(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody @Valid CategoryCreateRequest req) {
        return new ResponseEntity<>(categoryDtoMapper.apply(categoryService.create(req)), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CategoryDto> save(@RequestBody @Valid CategorySaveRequest req) {
        return new ResponseEntity<>(categoryDtoMapper.apply(categoryService.save(req)), HttpStatus.OK);
    }

}