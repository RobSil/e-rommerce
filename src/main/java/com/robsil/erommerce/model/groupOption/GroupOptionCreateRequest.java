package com.robsil.erommerce.model.groupOption;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GroupOptionCreateRequest {

    @NotEmpty
    private String sku;

    @NotEmpty
    private String groupId;

    @NotEmpty
    private String value;

    private boolean isActive;

}
