package com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Neighborhood {

    @Schema(description = "string or null")
    String id;

    @Schema(description = "string or null")
    String name;
}
