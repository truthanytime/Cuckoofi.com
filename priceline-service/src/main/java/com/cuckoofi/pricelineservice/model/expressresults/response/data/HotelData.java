package com.cuckoofi.pricelineservice.model.expressresults.response.data;

import com.cuckoofi.pricelineservice.model.expressavailability.response.data.City;
import com.cuckoofi.pricelineservice.model.expressavailability.response.data.ExternalUrl;
import com.cuckoofi.pricelineservice.model.expressavailability.response.data.RoomData;
import com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata.*;
import com.cuckoofi.pricelineservice.model.expressresults.response.data.HotelChain;
import com.cuckoofi.pricelineservice.model.hoteldetails.response.plugindata.PluginData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
public class HotelData {

    @Schema(description = "string or null, Hotel identifier.")
    String id = "";

    @Schema(description = "string or null")
    String id_t;

    @Schema(description = "integer, The id that can be mapped to identify a property type. See the Property Type Filter Guide for more detail.")
    Integer property_type_id;

    @Schema(description = "string or null, Hotel name.")
    String name;

    @Schema(description = "string or null, Default: ''")
    String hotel_description = "";

    @Schema(description = "number, Hotel's star rating. This is a number between 1 and 5. See more details in the Hotels Content Guide here.")
    String star_rating;

    @Schema(description = "number, Hotel review rating.")
    Number review_rating;

    @Schema(description = "string or null, Default: '', Review rating description.")
    String review_rating_desc = "";

    @Schema(description = "string or null")
    String thumbnail;

    @Schema(description = "object")
    ThumbnailHq thumbnail_hq;

    @Schema(description = "object, Hotel city name.")
    City city;

    @Schema(description = "object, The hotel address.")
    Address address;

    @Schema(description = "object, The geo location of hotel.")
    Geo geo;

    @Schema(description = "object, The hotel neighborhood information.")
    Neighborhood neighborhood;

    @Schema(description = "object, Hotel chain data.")
    HotelChain hotel_chain;

    @Schema(description = "object, Hotel amenity data.")
    AmenityData amenity_data;

    @Schema(description = "object, Review scores data cluster.")
    ReviewScoreData review_score_data;

    @Schema(description = "object, The schema for check-in information such as if standard check-in.")
    CheckInInformation check_in_information;

    @Schema(description = "object or null")
    PluginData plugin_data;

    @Schema(description = "Array of objects, Room data cluster.")
    List<RoomData> room_data;
}
