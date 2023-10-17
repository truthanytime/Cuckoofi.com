package com.cuckoofi.pricelineservice.model.expressresults.response.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PetFriendly {

    @Schema(description = "string")
    String plugin_id;

    @Schema(description = "string")
    String plugin_name;

    @Schema(description = "string")
    String hotelid_ppn;

    @Schema(description = "string")
    String policy;

    @Schema(description = "string")
    String policy_verified;

    @Schema(description = "string")
    String enable;

    @Schema(description = "string")
    String creation_date_time;
}
