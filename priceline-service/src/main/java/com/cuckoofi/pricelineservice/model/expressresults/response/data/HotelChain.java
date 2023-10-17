package com.cuckoofi.pricelineservice.model.expressresults.response.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class HotelChain {

    @Schema(description = "string, Hotel chain ID.")
    String id;

    @Schema(description = "integer or null, Hotel group ID.")
    Integer group_id;

    @Schema(description = "string or null, Hotel group name.")
    String name;

    @Schema(description = "integer or null, Hotel chain code.")
    Integer code;

    @Schema(description = "string or null")
    String chain_codes_b;

    @Schema(description = "string or null")
    String chain_codes_t;
}

