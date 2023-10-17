package com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ThumbnailHq {

    @Schema(description = "string or null")
    String hundred_fifty_square;

    @Schema(description = "string or null")
    String three_hundred_square;
}
