package com.cuckoofi.pricelineservice.model.expressavailability.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SortData {

    @Schema(description = "string, Indicates what criteria/ranking is used to sort the results. Note that the most_popular sort is based on the hotel_sort_score_ppn values in Hotel downloads.")
    String sort_by;
}
