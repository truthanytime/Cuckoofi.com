package com.cuckoofi.pricelineservice.model.autosuggestv2.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata.*;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Hotel implements Serializable {

    @Schema(description = "string, Default: ''.")
    String property_description = "";

    @Schema(description = "string, Default: ''.")
    String area_id = "";

    @Schema(description = "string, Default: ''.")
    String coordinate = "";

    @Schema(description = "string, Default: ''.")
    String type = "";

    @Schema(description = "string, Default: ''.")
    String hotel_name = "";

    @Schema(description = "string, Default: ''.")
    String hotelid_ppn = "";

    @Schema(description = "string, Default: ''.")
    String hotelid_b = "";

    @Schema(description = "integer, Default: 0.")
    Integer pet_count = 0;

    @Schema(description = "integer, Default: 0.")
    Integer rank = 0;

    @Schema(description = "string, Default: ''.")
    String thumbnail = "";

    @Schema(description = "object")
    Address address;

    @Schema(description = "string, Default: ''.")
    String area_name = "";

    @Schema(description = "string, Default: ''.")
    String cityid_ppn = "";

    @Schema(description = "string, Default: ''.")
    String hotelid_t = "";

    @Schema(description = "number, Hotel's star rating. This is a number between 1 and 5. See more details in the Hotels Content Guide here.")
    Number star_rating = 0;

    @Schema(description = "number, Default: 0.")
    Number review_rating = 0;

    @Schema(description = "number, Default: 0.")
    Number score = 0;

    @Schema(description = "string.")
    String id;

    @Schema(description = "number")
    Number _version_;

    @Schema(description = "string or null, Default: '', Review rating description.")
    String review_rating_desc = "";
}
