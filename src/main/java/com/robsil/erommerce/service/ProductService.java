package com.robsil.erommerce.service;

import com.robsil.erommerce.data.domain.Category;
import com.robsil.erommerce.data.domain.Product;
import com.robsil.erommerce.model.product.ProductCreateRequest;
import com.robsil.erommerce.model.product.ProductSaveRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface ProductService {

    Product findById(Long productId);
    Product findBySku(String sku);
    Page<Product> findAllByCategoryId(Long categoryId, Pageable pageable);
    Product create(ProductCreateRequest req, Category category);
    Product save(ProductSaveRequest req, Category category);

    // consider more about changing this method
    Product changeQuantity(Long productId, BigDecimal quantity);

    void deleteById(Long productId);
    void deleteAllByCategoryId(Long categoryId);

}
