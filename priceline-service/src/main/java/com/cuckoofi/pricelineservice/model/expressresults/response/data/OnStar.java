package com.cuckoofi.pricelineservice.model.expressresults.response.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OnStar {

    @Schema(description = "string")
    String plugin_id;

    @Schema(description = "string")
    String plugin_name;

    @Schema(description = "string")
    String hotelid_ppn;

    @Schema(description = "string")
    String phone_number;
}
