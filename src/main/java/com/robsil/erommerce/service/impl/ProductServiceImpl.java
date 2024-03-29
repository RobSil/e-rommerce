package com.robsil.erommerce.service.impl;

import com.robsil.erommerce.data.domain.Category;
import com.robsil.erommerce.data.domain.Product;
import com.robsil.erommerce.data.repository.ProductRepository;
import com.robsil.erommerce.model.exception.EntityNotFoundException;
import com.robsil.erommerce.model.exception.HttpConflictException;
import com.robsil.erommerce.model.product.ProductCreateRequest;
import com.robsil.erommerce.model.product.ProductSaveRequest;
import com.robsil.erommerce.service.ProductService;
import com.robsil.erommerce.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Product saveEntity(Product product) {
        if (StringUtil.isEmpty(product.getSku())) {
            int i = 0;
            DataIntegrityViolationException lastException = null;

            while (true) {
                if (i >= 10) {
                    if (lastException != null) {
                        throw lastException;
                    } else {
                        throw new HttpConflictException("Couldn't save product.");
                    }
                }
                i++;

                try {
                    product.setSku(StringUtil.minimize(product.getName() + "-" + StringUtil.randomAlphanumeric(15)));
                    return productRepository.save(product);
                } catch (DataIntegrityViolationException e) {
                    lastException = e;
                    log.info("saveEntity: got DataIntegrityViolationException. ProductName: %s".formatted(product.getName()));
                    log.info("saveEntity: exception message: %s".formatted(e.getMessage()));
                }
            }
        }

        return productRepository.save(product);
    }

    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> {
            log.info("findById: can't find product by ID: %s".formatted(productId));
            return new EntityNotFoundException("Product not found.");
        });
    }

    @Override
    public Product findBySku(String sku) {
        return productRepository.findBySku(sku).orElseThrow(() -> {
            log.info("findById: can't find product by SKU: %s".formatted(sku));
            return new EntityNotFoundException("Product not found.");
        });
    }

    @Override
    public Page<Product> findAllByCategoryId(Long categoryId, Pageable pageable) {
        return productRepository.findAllByCategoryId(categoryId, pageable);
    }

    @Override
    @Transactional
    public Product create(ProductCreateRequest req, Category category) {
        var product = Product.builder()
                .category(category)
                .name(req.getName())
                .sku(req.getSku())
                .price(req.getPrice())
                .quantity(req.getQuantity())
                .measureUnit(req.getMeasureUnit())
                .status(req.getStatus())
                .isActive(req.isActive())
                .build();

        product = saveEntity(product);

        return product;
    }

    @Override
    @Transactional
    public Product save(ProductSaveRequest req, Category category) {
        var product = findById(req.getId());

        product.setCategory(category);
        product.setName(req.getName());
        product.setSku(req.getSku());
        product.setPrice(req.getPrice());
        product.setQuantity(req.getQuantity());
        product.setMeasureUnit(req.getMeasureUnit());
        product.setStatus(req.getStatus());
        product.setActive(req.isActive());

        product = saveEntity(product);

        return product;
    }

    @Override
    @Transactional
    public Product changeQuantity(Long productId, BigDecimal quantity) {
        var product = findById(productId);

        product.setQuantity(quantity);

        product = saveEntity(product);

        return product;
    }

    @Override
    public void deleteById(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void deleteAllByCategoryId(Long categoryId) {
        productRepository.deleteAllByCategoryId(categoryId);
    }
}
