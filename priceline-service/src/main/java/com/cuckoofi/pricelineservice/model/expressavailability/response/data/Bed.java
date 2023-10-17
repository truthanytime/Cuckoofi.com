package com.cuckoofi.pricelineservice.model.expressavailability.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Bed {

    @Schema(description = "string, The type of bed. For example, a QUEEN or KING.")
    String type;

    @Schema(description = "string, Description of the bed.")
    String description;

    @Schema(description = "integer, Number of beds in this type.")
    Integer count;
}
