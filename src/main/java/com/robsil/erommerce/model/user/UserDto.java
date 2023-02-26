package com.robsil.erommerce.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.robsil.erommerce.model.ERole;
import com.robsil.erommerce.model.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDto {

    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("date_of_birth")
    private LocalDateTime dateOfBirth;

    private Gender gender;

    private boolean emailConfirmed = true;

    private List<ERole> roles = new ArrayList<>();

}
