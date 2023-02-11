package com.robsil.erommerce.data.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vendor {

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

    private String approvedAdminUserId;

    private String name;

    private String description;

    private int averageAmountOfEmployers;

    private String purpose;

    private String generalTypeOfProducts;

    private String contactEmail;

    private String contactPhoneNumber;

//    user ids of owners
//    private List<String> owners = new ArrayList<>();

//    private List<String> managers = new ArrayList<>();

//    private List<String> workers = new ArrayList<>();
}
