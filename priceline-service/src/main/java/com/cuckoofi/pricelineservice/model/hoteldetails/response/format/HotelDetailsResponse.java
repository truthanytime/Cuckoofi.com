package com.cuckoofi.pricelineservice.model.hoteldetails.response.format;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class HotelDetailsResponse implements Serializable {

    @Schema(description = "The results are grouped under this cluster.")
    HotelDetailsResponseResult results;
}
