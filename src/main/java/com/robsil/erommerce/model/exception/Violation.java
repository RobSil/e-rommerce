package com.robsil.erommerce.model.exception;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Violation {

    private String fieldName;
    private String message;

}
