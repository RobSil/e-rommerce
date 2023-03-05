package com.robsil.erommerce.model.cartItem;

import com.robsil.erommerce.model.product.ProductDto;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MinimizedCartItemDto {

    private ProductDto product;

    private BigDecimal quantity;

    private BigDecimal price;

}
