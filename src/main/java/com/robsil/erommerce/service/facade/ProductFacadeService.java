package com.robsil.erommerce.service.facade;

import com.robsil.erommerce.data.domain.Product;
import com.robsil.erommerce.model.product.ProductCreateRequest;
import com.robsil.erommerce.model.product.ProductSaveRequest;
import com.robsil.erommerce.service.CategoryService;
import com.robsil.erommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductFacadeService {

    private final ProductService productService;
    private final CategoryService categoryService;

    public Product create(ProductCreateRequest req) {
        var category = categoryService.findById(req.getCategoryId());

        return productService.create(req, category);
    }

    public Product save(ProductSaveRequest req) {
        var category = categoryService.findById(req.getCategoryId());

        return productService.save(req, category);
    }

}
