package com.cuckoofi.pricelineservice.model.autosuggestv2.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Region implements Serializable {

    @Schema(description = "string or null")
    String region_name;

    @Schema(description = "string, Default: ''.")
    String area_id = "";

    @Schema(description = "string, Default: ''.")
    String coordinate = "";

    @Schema(description = "string, Default: ''.")
    String cityids_ppn = "";

    @Schema(description = "string, Default: ''.")
    String type = "";

    @Schema(description = "string.")
    String id;

    @Schema(description = "number")
    Number _version_;

    @Schema(description = "number, Default: 0.")
    Number score = 0;
}
