package com.cuckoofi.pricelineservice.model.expressavailability.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PriceDetails {

    @Schema(description = "string")
    String baseline_currency;

    @Schema(description = "string")
    String baseline_symbol;

    @Schema(description = "number, Default: 0")
    Number baseline_price = 0;

    @Schema(description = "number, Subtotal is without taxes and fees.")
    Number baseline_sub_total;

    @Schema(description = "number")
    Number baseline_total;

    @Schema(description = "string, Customer is charged in source currency. See Price Details Guide for detailed explanations.")
    String source_currency;

    @Schema(description = "string, The source currency symbol.")
    String source_symbol;

    @Schema(description = "number, Source price of rate in source currency.")
    Number source_price;

    @Schema(description = "number, Subtotal in source currency. Subtotal is without taxes and fees.")
    Number source_sub_total;

    @Schema(description = "number, Total in source currency. Total is with applicable taxes and fees.")
    Number source_total;

    @Schema(description = "string, The display currency is for display purposes only. Customer is charged in source currency. See Price Details Guide for detailed explanations.")
    String display_currency;

    @Schema(description = "string")
    String display_symbol;

    @Schema(description = "number, Price of rate in display currency.")
    Number display_price;

    @Schema(description = "number, Subtotal is without taxes and fees.")
    Number display_sub_total;

    @Schema(description = "number, Total is with applicable taxes and fees.")
    Number display_total;
}
