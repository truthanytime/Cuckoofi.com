package com.cuckoofi.pricelineservice.model.hoteldetails.response.plugindata;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PetFriendly {

    @Schema(description = "string, Default: ''")
    String rank = "";

    @Schema(description = "string, Default: ''")
    String plugin_name = "";

    @Schema(description = "string, Default: ''")
    String hotelid_ppn = "";

    @Schema(description = "string, Default: ''")
    String policy = "";

    @Schema(description = "string, Default: ''")
    String policy_verified = "";

    @Schema(description = "string, Default: ''")
    String enable = "";

    @Schema(description = "string, Default: ''")
    String creation_date_time = "";
}
