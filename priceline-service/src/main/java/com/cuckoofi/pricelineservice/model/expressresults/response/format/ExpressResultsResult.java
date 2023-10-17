package com.cuckoofi.pricelineservice.model.expressresults.response.format;

import com.cuckoofi.pricelineservice.model.expressavailability.response.data.HotelData;
import com.cuckoofi.pricelineservice.model.expressavailability.response.data.ResultsData;
import com.cuckoofi.pricelineservice.model.expressavailability.response.data.SortData;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExpressResultsResult {

    @Schema(description = "string, A text status that represents the status of the request/response")
    String status;

    @Schema(description = "integer, An integer status code that represents the status of the request/response")
    Integer status_code;

    @Schema(description = "object")
    ResultsData results_data;

    @Schema(description = "object, The sorting information of the results.")
    SortData sort_data;

    @Schema(description = "Array of objects, Hotel data cluster.")
    List<HotelData> hotel_data;

    @Schema(description = "string, Indicates the language of the hotel and room details. Only selected data values are translated. See the Language Support Guide to learn more.")
    String language;

    @Schema(description = "number, Time elapsed.")
    Number time;
}
