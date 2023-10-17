package com.cuckoofi.pricelineservice.model.autosuggestv2.response.format;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutoSuggestV2ApiResponse implements Serializable {

    @Schema(description = "object, The Hotel AutoSuggestV2 response structure for a successful request.")
    @JsonProperty("getHotelAutoSuggestV2")
    AutoSuggestV2Response getHotelAutoSuggestV2;
}
