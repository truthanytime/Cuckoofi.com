package com.cuckoofi.pricelineservice.model.autosuggestv2.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Airport implements Serializable {

    @Schema(description = "string, Default: ''.")
    String active_car = "";

    @Schema(description = "string, Default: ''.")
    String coordinate = "";

    @Schema(description = "string, Default: ''.")
    String active_vp = "";

    @Schema(description = "string, Default: ''.")
    String city = "";

    @Schema(description = "string, Default: ''.")
    String active_air = "";

    @Schema(description = "integer, Default: 0.")
    Integer airport_id_ppn = 0;

    @Schema(description = "string, Default: ''.")
    String airport = "";

    @Schema(description = "Array of strings[ items >= 2 items unique ], Default: [], Airport Spelling Types. The first item in the array will be the IATA spelling and the second item will be the airport's full spelling.")
    List<String> airport_spell = new ArrayList<>();

    @Schema(description = "string, Default: ''.")
    String country_code = "";

    @Schema(description = "string, Default: ''.")
    String iata = "";

    @Schema(description = "string, Default: ''.")
    String cityid_ppn = "";

    @Schema(description = "string, Default: ''.")
    String active_hotel = "";

    @Schema(description = "string, Default: ''.")
    String icao = "";

    @Schema(description = "integer, Default: 0.")
    Integer rank = 0;

    @Schema(description = "string or null")
    String state_code;

    @Schema(description = "string, Default: ''.")
    String type = "";

    @Schema(description = "string")
    String id;

    @Schema(description = "integer")
    Integer _version;

    @Schema(description = "string, Default: ''.")
    String score = "";
}
