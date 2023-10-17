package com.cuckoofi.pricelineservice.model.expressavailability.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BenchmarkPriceDetails {

    @Schema(description = "integer, Default: 0")
    Integer saving_percentage = 0;

    @Schema(description = "string")
    String baseline_currency;

    @Schema(description = "integer, Default: 0")
    Integer baseline_price = 0;

    @Schema(description = "string")
    String source_currency;

    @Schema(description = "integer, Default: 0")
    Integer source_price = 0;

    @Schema(description = "string")
    String display_currency;

    @Schema(description = "integer, Default: 0")
    Integer display_price = 0;
}
