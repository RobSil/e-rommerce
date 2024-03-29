package com.robsil.erommerce.service.dtomapper;

import com.robsil.erommerce.data.domain.Product;
import com.robsil.erommerce.model.product.ProductDto;
import com.robsil.erommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

        var result = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .measureUnit(product.getMeasureUnit())
                .status(product.getStatus())
                .isActive(product.isActive())
                .build();

        if (product.getCategory() != null) {
            result.setCategory(categoryDtoMapper.apply(product.getCategory()));
        }

        return result;
    }
}
