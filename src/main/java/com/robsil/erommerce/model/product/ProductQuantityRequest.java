package com.robsil.erommerce.model.product;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductQuantityRequest {

    @NotEmpty
    private Long productId;

    private BigDecimal quantity;

}
