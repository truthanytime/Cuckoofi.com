package com.cuckoofi.pricelineservice.model.expressavailability.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultsData {

    @Schema(description = "integer, Default: 0")
    Integer rate_count = 0;

    @Schema(description = "integer, Default: 0")
    Integer nearby_rate_count = 0;
}
