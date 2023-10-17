package com.cuckoofi.pricelineservice.model.expressresults.response.format;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpressResultsResponse implements Serializable {

    @Schema(description = "object, The results are grouped under this cluster.")
    ExpressResultsResult results;

    @Schema(description = "object, The error object.")
    Object error;
}
