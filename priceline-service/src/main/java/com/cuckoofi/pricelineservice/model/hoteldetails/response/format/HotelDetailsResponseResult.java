package com.cuckoofi.pricelineservice.model.hoteldetails.response.format;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata.*;
import java.io.Serializable;
import java.util.List;

@Data
public class HotelDetailsResponseResult implements Serializable {

    @Schema(description = "A text status that represents the status of the request/response")
    String status;

    @Schema(description = "An integer status code that represents the status of the request/response")
    Integer status_code;

    @Schema(description = "Hotel data cluster.")
     List<HotelData> hotel_data;

    @Schema(description = "Time elapsed to generate the response.")
    String time;
}
