package com.cuckoofi.pricelineservice.model.expressavailability.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class City implements Serializable {

    @Schema(description = "string or null, The city ID.")
    String id;

    @Schema(description = "string or null, The city name")
    String name;
}
