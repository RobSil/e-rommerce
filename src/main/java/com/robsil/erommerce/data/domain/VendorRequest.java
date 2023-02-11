package com.robsil.erommerce.data.domain;

import com.robsil.erommerce.model.user.UserVendorRequest;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import java.security.Principal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VendorRequest {

    @Id
    private String id;

    @CreatedDate
    @Indexed(direction = IndexDirection.ASCENDING)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @CreatedBy
    private Principal createdBy;

    @Version
    private long version;

    @Indexed(unique = true)
    private String userId;

    private UserVendorRequest request;

    private boolean isApproved;

    private String approvedBy;

}
