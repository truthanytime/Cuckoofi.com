package com.cuckoofi.pricelineservice.model.autosuggestv2.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Poi implements Serializable {

    @Schema(description = "integer, Default: 0.")
    Integer cat_id = 0;

    @Schema(description = "string or null")
    String state_code;

    @Schema(description = "string, Default: ''.")
    String coordinate = "";

    @Schema(description = "string, Default: ''.")
    String address = "";

    @Schema(description = "string, Default: ''.")
    String poi_name = "";

    @Schema(description = "string, Default: ''.")
    String state = "";

    @Schema(description = "string, Default: ''.")
    String type = "";

    @Schema(description = "string, Default: ''.")
    String country_code = "";

    @Schema(description = "string, Default: ''.")
    String poiid_ppn = "";

    @Schema(description = "string, Default: ''.")
    String country = "";

    @Schema(description = "string, Default: ''.")
    String city = "";

    @Schema(description = "string.")
    String id;

    @Schema(description = "number")
    Number _version_;

    @Schema(description = "number, Default: 0.")
    Number score = 0;
}
