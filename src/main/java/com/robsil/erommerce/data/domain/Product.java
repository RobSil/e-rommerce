package com.robsil.erommerce.data.domain;

import com.robsil.erommerce.model.ProductStatus;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Product")
public class Product {

    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    private Principal createdBy;

    @Version
    private long version;

    @Indexed
    private String categoryId;

    private String name;

    private String sku;

    private BigDecimal price;

    private int quantity;

    private ProductStatus status;

    private String groupId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return quantity == product.quantity && Objects.equals(id, product.id) && Objects.equals(categoryId, product.categoryId) && Objects.equals(name, product.name) && Objects.equals(sku, product.sku) && Objects.equals(price, product.price) && status == product.status && Objects.equals(groupId, product.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId, name, sku, price, quantity, status, groupId);
    }
}
