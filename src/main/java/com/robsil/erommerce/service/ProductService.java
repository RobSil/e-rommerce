package com.robsil.erommerce.service;

import com.robsil.erommerce.data.domain.Category;
import com.robsil.erommerce.data.domain.Product;
import com.robsil.erommerce.model.product.ProductCreateRequest;
import com.robsil.erommerce.model.product.ProductSaveRequest;

import java.util.List;

public interface ProductService {

    Product findById(String productId);
    List<Product> findAllByCategoryId(String categoryId);
    Product create(ProductCreateRequest req, Category category);
    Product save(ProductSaveRequest req, Category category);

    // consider more about changing this method
    Product changeQuantity(String productId, int quantity);

}
