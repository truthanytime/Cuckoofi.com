package com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReviewData {

    @Schema(description = "string")
    String inventory;

    @Schema(description = "string")
    String user_name;

    @Schema(description = "string")
    String good_description;

    @Schema(description = "string")
    String bad_description;

    @Schema(description = "string")
    String review_text;

    @Schema(description = "string")
    String average_rating;

    @Schema(description = "string")
    String average_rating_description;

    @Schema(description = "string")
    String traveller_type_name;

    @Schema(description = "string")
    String creation_date;
}
