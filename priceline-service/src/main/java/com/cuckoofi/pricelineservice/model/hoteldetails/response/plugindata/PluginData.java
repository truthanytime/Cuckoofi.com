package com.cuckoofi.pricelineservice.model.hoteldetails.response.plugindata;

import com.cuckoofi.pricelineservice.model.expressresults.response.data.PetFriendly;
import com.cuckoofi.pricelineservice.model.expressresults.response.data.OnStar;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PluginData {

    @Schema(description = "object, If pets are allowed in the property.")
    PetFriendly pet_friendly;

    @Schema(description = "object, If the hotel has a green policy score.")
    PetFriendly green;

    @Schema(description = "object")
    OnStar on_star;
}
