package com.robsil.erommerce.data.domain;

import com.robsil.erommerce.model.VendorEmployeePosition;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.security.Principal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendorEmployee {

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

    @Indexed
    private String userId;

    @Indexed
    private String vendorId;

    private VendorEmployeePosition position;

}
