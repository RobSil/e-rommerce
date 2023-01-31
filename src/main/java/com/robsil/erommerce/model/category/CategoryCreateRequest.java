package com.robsil.erommerce.model.category;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CategoryCreateRequest {

    private String parentId;

    @NotEmpty
    private String title;

}
