package com.cuckoofi.pricelineservice.model.autosuggestv2.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class City implements Serializable {

    @Schema(description = "string, Default: ''.")
    String country = "";

    @Schema(description = "string, Default: ''.")
    String coordinate = "";

    @Schema(description = "string, Default: ''.")
    String city = "";

    @Schema(description = "number, Default: 0.")
    Number latitude = 0;

    @Schema(description = "string, Default: ''.")
    String nearby_airports_json = "";

    @Schema(description = "string, Default: ''.")
    String country_code = "";

    @Schema(description = "integer, Default: 0.")
    Integer pet_count = 0;

    @Schema(description = "string, Default: ''.")
    String cityid_ppn = "";

    @Schema(description = "integer, Default: 0.")
    Integer rank = 0;

    @Schema(description = "integer, Default: 0.")
    Integer hotel_count = 0;

    @Schema(description = "string, Default: ''.")
    String state = "";

    @Schema(description = "string or null")
    String state_code;

    @Schema(description = "string, Default: ''.")
    String type = "";

    @Schema(description = "number, Default: 0.")
    Number longitude = 0;

    @Schema(description = "string.")
    String id;

    @Schema(description = "number")
    Number _version_;

    @Schema(description = "number, Default: 0.")
    Number score = 0;
}
