package com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class HotelChain {

    @Schema(description = "string, Hotel chain ID.")
    String id;

    @Schema(description = "string or null, Hotel group name.")
    String name;

    @Schema(description = "string or null")
    String chain_codes_b;

    @Schema(description = "string or null")
    String chain_codes_t;
}
