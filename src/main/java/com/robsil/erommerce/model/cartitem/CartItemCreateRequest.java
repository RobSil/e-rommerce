package com.robsil.erommerce.model.cartitem;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CartItemCreateRequest {

    @NotNull
    @Positive
    private Long productId;

    @NotNull
    @Positive
    private BigDecimal quantity;

}
