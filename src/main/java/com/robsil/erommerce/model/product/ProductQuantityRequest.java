package com.robsil.erommerce.model.product;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductQuantityRequest {

    @NotEmpty
    private String productId;

    private int quantity;

}
