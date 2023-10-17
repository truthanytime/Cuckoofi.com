package com.cuckoofi.pricelineservice.model.expressavailability.response.format;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpressAvailabilityResponse implements Serializable {

    @Schema(description = "object, The results are grouped under this cluster.")
    ExpressAvailabilityResult results;

    @Schema(description = "object, The error object.")
    Object error;
}
