package com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class City {
    @Schema(description = "string or null, The city ID.")
    String id;

    @Schema(description = "string or null, The city name")
    String name;
}
