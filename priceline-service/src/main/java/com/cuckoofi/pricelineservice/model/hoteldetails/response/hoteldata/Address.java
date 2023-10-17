package com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Address {

    @Schema(description = "string or null")
    String address_line_one;

    @Schema(description = "string or null, The city name")
    String city_name;

    @Schema(description = "string or null")
    String state_code;

    @Schema(description = "string or null")
    String state_name;

    @Schema(description = "string or null")
    String country_code;

    @Schema(description = "string or null")
    String country_name;

    @Schema(description = "string or null")
    String zip;
}
