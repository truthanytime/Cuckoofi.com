package com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AmenityData {

    @Schema(description = "string, Default: ''")
    String id = "";

    @Schema(description = "string, Default: ''")
    String name = "";
}
