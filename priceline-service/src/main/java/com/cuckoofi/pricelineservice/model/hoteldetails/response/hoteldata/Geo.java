package com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Geo {
    @Schema(description = "number or null, Default: 0")
    Number latitude = 0;

    @Schema(description = "number or null, Default: 0")
    Number longitude = 0;
}
