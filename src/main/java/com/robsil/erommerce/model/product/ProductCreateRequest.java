package com.robsil.erommerce.model.product;

import com.robsil.erommerce.model.ProductStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductCreateRequest {

    @NotEmpty
    private Long categoryId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String sku;

    @NotNull
    private BigDecimal price;

    private BigDecimal quantity;

    private String measureUnit;

    @NotNull
    private ProductStatus status;

    private Long groupId;

    private boolean isActive;

}
