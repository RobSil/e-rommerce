package com.robsil.erommerce.model.product;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ProductCheckSkuResponse {

    private boolean isExists;

//    not null, if isExists == true
    private ProductDto product;

}
