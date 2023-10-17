package com.cuckoofi.pricelineservice.model.expressavailability.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExternalUrl {

    @Schema(description = "string, The type of the external link.")
    String type;

    @Schema(description = "string, The supplier who provides the inventory and the rates.")
    String inventory;

    @Schema(description = "string, The linked URL with the search criteria that directs a user to the supplier's website.")
    String url;

    @Schema(description = "object, Price summary for the lowest rate available from this inventory supplier.")
    PriceSummary price_summary;
}
