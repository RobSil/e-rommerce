package com.robsil.erommerce.model.group;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GroupSaveRequest {

    @NotEmpty
    private String id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String name;

}
