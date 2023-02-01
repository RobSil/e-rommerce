package com.robsil.erommerce.model.group;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GroupCreateRequest {

    @NotEmpty
    private String title;

    @NotEmpty
    private String name;

}
