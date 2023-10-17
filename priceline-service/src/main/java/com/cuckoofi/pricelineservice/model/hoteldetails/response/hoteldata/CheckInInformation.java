package com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CheckInInformation {

    @Schema(description = "boolean, Indicates whether the property provides traditional check-in such as a hotel/reception desk. See the Check-in information guide section for more details.")
    Boolean standard_check_in;
}
