package com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RankData {

    @Schema(description = "string or null, Default: ''")
    String rank = "";

    @Schema(description = "string or null, Default: ''")
    String rank_beta = "";
}
