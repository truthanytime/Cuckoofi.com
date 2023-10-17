package com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata;

import com.cuckoofi.pricelineservice.model.autosuggestv2.response.data.City;
import com.cuckoofi.pricelineservice.model.hoteldetails.response.plugindata.PluginData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HotelData {

    @Schema(description = "string, Default: '', Hotel ID.")
    String id = "";

    @Schema(description = "string or null")
    String id_a;

    @Schema(description = "string or null")
    String id_b;

    @Schema(description = "string or null")
    String id_t;

    @Schema(description = "string or null")
    String id_r;

    @Schema(description = "boolean or null, If the hotel is active.")
    Boolean active;

    @Schema(description = "Array of strings or null, The inventories in which the hotel is available.")
    List<String> inventory;

    @Schema(description = "string or null, Hotel name.")
    String name;

    @Schema(description = "string or null, The id that can be mapped to identify a property type. See the Property Type Filter Guide for more detail.")
    String property_type;

    @Schema(description = "integer, Default: 0, This will return 1 if PCLN discounted rates are available for this property.")
    Integer semi_opq_enabled = 0;

    @Schema(description = "string, Hotel's star rating. This is a value between 1 and 5. See more details in the Hotels Content Guide here.")
    String star_rating;

    @Schema(description = "number, Default: 0")
    Integer review_rating = 0;

    @Schema(description = "string or null, Default: '', Review rating description.")
    String review_rating_desc = "";

    @Schema(description = "integer or null, Number of reviews received.")
    Integer review_count;

    @Schema(description = "integer or null")
    Integer rating_count;

    @Schema(description = "string or null")
    String review_source;

    @Schema(description = "string or null, Hotel page URL in Agoda.com")
    String agoda_url;

    @Schema(description = "string or null, Hotel page URL in Booking.com")
    String booking_url;

    @Schema(description = "string or null, Hotel page URL in Priceline.com")
    String priceline_url;

    @Schema(description = "object, Hotel price information (average lowest rate that may vary in actual rate call)")
    StaticRowRate static_low_rate;

    @Schema(description = "string or null")
    String thumbnail;

    @Schema(description = "object, Thumbnail image for hotel.")
    ThumbnailHq thumbnail_hq;

    @Schema(description = "object, Hotel's city ID and name.")
    City city;

    @Schema(description = "object, Hotel address.")
    Address address;

    @Schema(description = "object, Geo location codes of the hotel. Can be used to display the map location.")
    Geo geo;

    @Schema(description = "object, Hotel chain information when applicable.")
    HotelChain hotelChain;

    @Schema(description = "string or null, Default '', Standard check-in before time of the hotel.")
    String check_in_time = "";

    @Schema(description = "string or null, Default '', Standard check-out after time of the hotel.")
    String check_out_time = "";

    @Schema(description = "string, Default 'en', Language of hotel content.")
    String language = "en";

    @Schema(description = "string or null, Default ''")
    String hotel_description = "";

    @Schema(description = "string or null, Default '', Number of total rooms in the property.")
    String room_count = "";

    @Schema(description = "object, Hotel rank data used in sorting.")
    HotelRank hotel_rank;

    @Schema(description = "object, Hotel rank data.")
    RankData rank_data;

    @Schema(description = "object, Review scores data cluster.")
    ReviewScoreData review_score_data;

    @Schema(description = "object, The hotel neighborhood information.")
    Neighborhood neighborhood;

    @Schema(description = "Array of objects, Only available if reviews=true is passed in request.")
    List<ReviewData> review_data;

    @Schema(description = "integer, Default 0, This will return 1 if this property provides discounted rates for closed user groups.")
    Integer cug_enabled = 0;

    @Schema(description = "object, The schema for check-in information such as if standard check-in.")
    CheckInInformation check_in_information;

    @Schema(description = "Array of strings, Only available if photos=true is passed in request.")
    List<String> photo_data;

    @Schema(description = "Array of objects, Only available if recent=true is passed in request.")
    List<RecentSalesData> recent_sales_data;

    @Schema(description = "object or Array of objects, Additional plugin policies.")
    List<PluginData> plugin_data;

    @Schema(description = "Array of objects, Default: []")
    List<WarningData> warning_data = new ArrayList<WarningData>();
}
