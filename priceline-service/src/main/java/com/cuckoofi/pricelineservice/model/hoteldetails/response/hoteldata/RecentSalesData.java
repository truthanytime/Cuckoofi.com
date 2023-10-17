package com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RecentSalesData {

    @Schema(description = "string")
    String city_id;

    @Schema(description = "string")
    String hotel_id;

    @Schema(description = "string")
    String rate;

    @Schema(description = "string")
    String creation_date_time;

    @Schema(description = "string")
    String creation_date_time_iso8601;

    @Schema(description = "string")
    String time_from_last;
}
