package com.robsil.erommerce.model.product;

import com.robsil.erommerce.model.ProductStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductSaveRequest {

    @NotEmpty
    private String id;

    @NotEmpty
    private String categoryId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String sku;

    @NotNull
    private BigDecimal price;

    private int quantity;

    @NotNull
    private ProductStatus status;

    private String groupId;

    private boolean isActive;

}
