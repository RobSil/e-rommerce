package com.robsil.erommerce.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserVendorResponse {

    @JsonProperty("approximate_processing_time_min")
    private int approximateProcessingTimeMin;
    @JsonProperty("approximate_processing_time_max")
    private int approximateProcessingTimeMax;

}
