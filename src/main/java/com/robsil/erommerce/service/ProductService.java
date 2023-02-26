package com.robsil.erommerce.service;

import com.robsil.erommerce.data.domain.Category;
import com.robsil.erommerce.data.domain.Product;
import com.robsil.erommerce.model.product.ProductCheckSkuResponse;
import com.robsil.erommerce.model.product.ProductCreateRequest;
import com.robsil.erommerce.model.product.ProductSaveRequest;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    Product findById(Long productId);
    Product findBySku(String sku);
    List<Product> findAllByCategoryId(Long categoryId);
    Product create(ProductCreateRequest req, Category category);
    Product save(ProductSaveRequest req, Category category);

    // consider more about changing this method
    Product changeQuantity(Long productId, BigDecimal quantity);

}
