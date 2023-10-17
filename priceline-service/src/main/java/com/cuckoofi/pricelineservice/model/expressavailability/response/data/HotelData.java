package com.cuckoofi.pricelineservice.model.expressavailability.response.data;

import com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata.Address;
import com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata.Geo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class HotelData {

    @Schema(description = "string or null, Hotel identifier.")
    String id = "";

    @Schema(description = "string or null, Hotel name.")
    String name;

    @Schema(description = "number, Hotel's star rating. This is a number between 1 and 5. See more details in the Hotels Content Guide here.")
    String star_rating;

    @Schema(description = "number, Default: 0, Hotel review rating.")
    Number review_rating = 0;

    @Schema(description = "string or null, Default: '', Review rating description.")
    String review_rating_desc = "";

    @Schema(description = "string or null")
    String thumbnail;

    @Schema(description = "object, Hotel city details.")
    City city;

    @Schema(description = "object, The hotel address.")
    Address address;

    @Schema(description = "object, The geo location of hotel.")
    Geo geo;

    @Schema(description = "Array of objects, Provides the deeplink URLs. This is a feature specific to deep linking option and may not always be available in the response schema.")
    List<ExternalUrl> external_urls;

    @Schema(description = "Array of objects, Room data cluster.")
    List<RoomData> room_data;
}
