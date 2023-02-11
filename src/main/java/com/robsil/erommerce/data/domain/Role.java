package com.robsil.erommerce.data.domain;

import com.robsil.erommerce.model.ERole;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Role")
public class Role {

    @Id
    private String id;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    private Principal createdBy;

    @Version
    private long version;

    @Indexed(unique = true)
    private ERole name;

    private String title;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) && Objects.equals(title, role.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
