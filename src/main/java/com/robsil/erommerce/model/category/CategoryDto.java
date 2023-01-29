package com.robsil.erommerce.model.category;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String id;

    private CategoryDto parent;

    private String title;

}
