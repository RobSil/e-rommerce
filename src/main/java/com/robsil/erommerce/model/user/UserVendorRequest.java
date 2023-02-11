package com.robsil.erommerce.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserVendorRequest {

    @NotEmpty
    private String name;
    @NotEmpty
    @JsonProperty("about_company")
    private String aboutCompany;
    @NotEmpty
    @JsonProperty("average_amount_of_employers")
    private int averageAmountOfEmployers;
    @NotEmpty
    private String purpose;
    @NotEmpty
    @JsonProperty("general_type_of_products")
    private String generalTypeOfProducts;
    @NotEmpty
    @JsonProperty("contact_email")
    private String contactEmail;
    @NotEmpty
    @JsonProperty("contact_phone_number")
    private String contactPhoneNumber;

}
