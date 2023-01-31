package com.robsil.erommerce.service.dtoMapper;

import com.robsil.erommerce.data.domain.Category;
import com.robsil.erommerce.data.domain.Product;
import com.robsil.erommerce.model.ProductStatus;
import com.robsil.erommerce.model.category.CategoryDto;
import com.robsil.erommerce.model.exception.EntityNotFoundException;
import com.robsil.erommerce.model.product.ProductDto;
import com.robsil.erommerce.service.CategoryService;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class ProductDtoMapper implements Function<Product, ProductDto> {

    private final CategoryService categoryService;
    private final CategoryDtoMapper categoryDtoMapper;

    @Override
    public ProductDto apply(Product product) {
        if (product == null) {
            return null;
        }

        Category category = null;

        try {
            category = categoryService.findById(product.getCategoryId());
        } catch (EntityNotFoundException ignored) {}

        var result = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .status(product.getStatus())
                .groupId(product.getGroupId())
                .isActive(product.isActive())
                .build();

        if (category != null) {
            result.setCategory(categoryDtoMapper.apply(category));
        }

        return result;
    }
}
