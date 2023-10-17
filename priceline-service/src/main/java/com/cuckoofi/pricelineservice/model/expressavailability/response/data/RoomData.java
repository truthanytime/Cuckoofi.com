package com.cuckoofi.pricelineservice.model.expressavailability.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoomData {

    @Schema(description = "string, Room identifier.")
    String id;

    @Schema(description = "string, The title of room.")
    String title;

    @Schema(description = "string, Room description.")
    String description;

    @Schema(description = "number or null, Room size in square footage.")
    String room_square_footage;

    @Schema(description = "object, Bedding data. Only available if room_bedding=1 is passed in request.")
    BeddingData bedding_data;

    @Schema(description = "Array of objects, See the Rate Information Guide for more details about this data cluster.")
    List<RateData> rate_data;
}
