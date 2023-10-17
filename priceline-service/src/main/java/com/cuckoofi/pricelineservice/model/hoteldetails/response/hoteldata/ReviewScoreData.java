package com.cuckoofi.pricelineservice.model.hoteldetails.response.hoteldata;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ReviewScoreData {

    @Schema(description = "string or null, The cleanliness score of the hotel.")
    String cleanliness_score;
}
