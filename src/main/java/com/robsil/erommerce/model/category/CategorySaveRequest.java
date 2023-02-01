package com.robsil.erommerce.model.category;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CategorySaveRequest {

    @NotEmpty
    private String id;

    private String parentId;

    @NotEmpty
    private String title;

}
