package com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class HotelRank {

    @Schema(description = "integer or null, Default: 0")
    Integer rank = 0;

    @Schema(description = "integer or null, Default: 0")
    Integer reviewed_hotel = 0;

    @Schema(description = "number or null, Default: 0")
    Number hmi_score = 0;

    @Schema(description = "integer or null, Default: 0")
    Integer rank_score = 0;
}
