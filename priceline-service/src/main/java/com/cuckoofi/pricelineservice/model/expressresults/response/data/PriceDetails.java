package com.cuckoofi.pricelineservice.model.expressresults.response.data;

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

    @Schema(description = "number")
    Number baseline_price;

    @Schema(description = "number, The price of any property fees collected for all rooms and nights.")
    Number baseline_property_fee;

    @Schema(description = "number")
    Number baseline_insurance_fee;

    @Schema(description = "number, Subtotal is without taxes and fees.")
    Number baseline_sub_total;

    @Schema(description = "number")
    Number baseline_taxes;

    @Schema(description = "number")
    Number baseline_total;

    @Schema(description = "string, Customer is charged in source currency. See Price Details Guide for detailed explanations.")
    String source_currency;

    @Schema(description = "string, The source currency symbol.")
    String source_symbol;

    @Schema(description = "number, Source price of rate in source currency.")
    Number source_price;

    @Schema(description = "number, The source price of any property fees collected for all rooms and nights.")
    Number source_property_fee;

    @Schema(description = "number, The source insurance fee value.")
    Number source_insurance_fee;

    @Schema(description = "number, Subtotal in source currency. Subtotal is without taxes and fees.")
    Number source_sub_total;

    @Schema(description = "number, Default: 0, Taxes in source currency.")
    Number source_taxes = 0;

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
