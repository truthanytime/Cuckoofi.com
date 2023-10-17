package com.cuckoofi.pricelineservice.model.hoteldetails.response.plugindata;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OnStar {

    @Schema(description = "string, Default: ''")
    String rank = "";

    @Schema(description = "string, Default: ''")
    String plugin_name = "";

    @Schema(description = "string, Default: ''")
    String hotelid_ppn = "";

    @Schema(description = "string, Default: ''")
    String phone_number = "";
}
