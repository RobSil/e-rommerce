package com.robsil.erommerce.model.groupOption;

import com.robsil.erommerce.model.ProductStatus;
import com.robsil.erommerce.model.product.ProductDto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GroupOptionDto {

    private String id;

    private ProductDto product;

    private String groupId;

    private String value;

    private boolean isActive;

    private ProductStatus status;

}
