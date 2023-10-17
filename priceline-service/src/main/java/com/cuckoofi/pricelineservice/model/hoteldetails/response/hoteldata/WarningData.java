package com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class WarningData {

    @Schema(description = "string, Default: ''")
    String status = "";

    @Schema(description = "integer, Default: 0")
    Integer status_code = 0;

    @Schema(description = "string, Default: ''")
    String status_message = "";
}
