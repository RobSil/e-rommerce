package com.robsil.erommerce.model.groupOption;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GroupOptionSaveRequest {

    @NotEmpty
    private String sku;

    @NotEmpty
    private String groupId;

    @NotEmpty
    private String value;

    private boolean isActive;

}
