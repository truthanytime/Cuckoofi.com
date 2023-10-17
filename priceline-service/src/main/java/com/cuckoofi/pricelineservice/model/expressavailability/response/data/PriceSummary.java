package com.cuckoofi.pricelineservice.model.expressavailability.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceSummary {

    @Schema(description = "string, The display currency for the rate. See the Price Details Guide for a detailed explanation of price breakdown.")
    String display_currency;

    @Schema(description = "string, Display currency symbol.")
    String display_symbol;

    @Schema(description = "number, Price of rate in display currency.")
    String display_price;
}
