package com.cuckoofi.pricelineservice.model.expressresults.response.data;

import com.cuckoofi.pricelineservice.model.expressavailability.response.data.BenchmarkPriceDetails;
import com.cuckoofi.pricelineservice.model.expressavailability.response.data.PriceDetails;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RateData {

    @Schema(description = "string, The inventory source.")
    String source;

    @Schema(description = "string or null, The rate type.")
    String rate_type;

    @Schema(description = "string")
    String commission_type;

    @Schema(description = "string or null")
    String distribution_type;

    @Schema(description = "string, See the Rate Information Guide for more details about payment types.")
    String payment_type;

    @Schema(description = "string, Default: 'NONE', The board type of the rate such as whether the rate is breakfast only or all inclusive. See the Rate Information Guide for more details.")
    String board_type = "NONE";

    @Schema(description = "string, Default: 'UNKNOWN'")
    String inventory_type = "UNKNOWN";

    @Schema(description = "Array of strings, Array of strings Items Enum: 'MOBILE_EXCLUSIVE' 'GENIUS' 'MEMBER_DEALS', Program type of rate if applicable. See the Rate Information Guide for more details about program types.")
    List<String> program_types;

    @Schema(description = "integer or null, The occupancy limit for number of adults + children. See the Rate Information Guide for more details.")
    Integer occupancy_limit;

    @Schema(description = "integer or null, The number of available rooms at this rate. See the Rate Information Guide for more details.")
    Integer available_rooms;

    @Schema(description = "string, The bundle value binds together the search criteria for reference in a subsequent API call.")
    String ppn_bundle;

    @Schema(description = "string or null, Default ''.")
    String rate_plan_code = "";

    @Schema(description = "string or null, Default ''.")
    String rate_tracking_id = "";

    @Schema(description = "object")
    BenchmarkPriceDetails benchmark_price_details;

    @Schema(description = "object, The breakdown of the price details for the rate. See Price Details Guide for detailed explanations.")
    PriceDetails price_details;

    @Schema(description = "string, Enum: 'NONE' 'PARTIAL' 'FULL', Indicates the refund type as whether full refund, partial refund or no refund.")
    String refund_type;

    @Schema(description = "string, Default: 'en-US', Indicates the language of the delivered rate data values. Only selected data values are translated. See the Language Support Guide for more details.")
    String language = "en-US";
}
