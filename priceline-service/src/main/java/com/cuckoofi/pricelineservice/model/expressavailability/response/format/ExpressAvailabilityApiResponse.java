package com.cuckoofi.pricelineservice.model.expressavailability.response.format;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpressAvailabilityApiResponse implements Serializable {

    @Schema(description = "object, The Express.Availability response structure for a successful request.")
    @JsonProperty("getHotelExpress.Availability")
    ExpressAvailabilityResponse expressAvailabilityResponse;
}
