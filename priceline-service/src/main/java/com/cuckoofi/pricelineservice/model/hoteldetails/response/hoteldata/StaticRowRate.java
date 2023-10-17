package com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class StaticRowRate {

    @Schema(description = "string or null")
    String source_currency;

    @Schema(description = "string or null")
    String source_symbol;

    @Schema(description = "(number or null) or (string or null)")
    String source_price;

    @Schema(description = "string or null")
    String display_currency;

    @Schema(description = "string or null")
    String display_symbol;

    @Schema(description = "(number or null) or (string or null)")
    String display_price;
}
