package com.cuckoofi.pricelineservice.model.expressresults.response.format;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpressResultsApiResponse implements Serializable {

    @Schema(description = "object, The Express.Results response structure for a successful request.")
    @JsonProperty("getHotelExpress.Results")
    ExpressResultsResponse expressResultsResponse;
}
