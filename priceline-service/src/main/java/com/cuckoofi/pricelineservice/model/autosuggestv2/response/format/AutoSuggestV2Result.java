package com.cuckoofi.pricelineservice.model.autosuggestv2.response.format;

import com.cuckoofi.pricelineservice.model.autosuggestv2.response.data.AutoSuggestV2;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutoSuggestV2Result {

    @Schema(description = "string, A text status that represents the status of the request/response")
    String status;

    @Schema(description = "integer, An integer status code that represents the status of the request/response")
    Integer status_code;

    @Schema(description = "object, The AutoSuggestV2 search result.")
    AutoSuggestV2 result;

    @Schema(description = "number, Time elapsed to generate a response.")
    Number time;
}
