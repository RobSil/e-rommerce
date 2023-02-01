package com.robsil.erommerce.model.product;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductQuantityRequest {

    @NotEmpty
    private String productId;

    private int quantity;

}
