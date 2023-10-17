package com.cuckoofi.pricelineservice.model.expressavailability.request;

import com.cuckoofi.commonclientlibs.validation.ValidDate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;

@Data
public class ExpressAvailabilityRequest implements Serializable {

    @Schema(description = "Default: 'json2', Enum: 'xml' 'json' 'json2', The format of your request. We recommend requesting json2 format response.")
    @Pattern(regexp = "^(xml|json|json2)$", message = "Invalid format. Use 'xml', 'json', or 'json2'.")
    String format = "json2";

    @Schema(description = "string <date>, Example: check_in=2023-09-19, Check In Date (YYYY-MM-DD or MM/DD/YYYY)")
    @NotNull(message = "Check-in date is required")
    @ValidDate
    String check_in;

    @Schema(description = "string <date>, Example: check_out=2023-09-20, Check Out Date (YYYY-MM-DD or MM/DD/YYYY)")
    @NotNull(message = "Check-out date is required")
    @ValidDate
    String check_out;

    @Schema(description = "string^[0-9]+$, Accepts a single PPN City ID.")
    @Pattern(regexp = "^[0-9]+$", message = "City ID must contain only numeric digits.")
    String city_id;

    @Schema(description = "string^[a-zA-Z]{3}$, Accepts a 3-character IATA airport code.")
    @Pattern(regexp = "^[a-zA-Z]{3}$", message = "Invalid airport code format. Use a 3-character IATA code.")
    String airport_code;

    @Schema(description = "string^[\\d\\,]+$, Comma separated string of PPN hotel ids.")
    @Pattern(regexp = "^[\\d,]+$", message = "Invalid hotel IDs format.")
    String hotel_ids;

    @Schema(description = "string^[\\-]?[\\d\\.]+$, Search for properties availability around a specific latitude coordinate. We recommend that you use the radius parameter when performing a latitude/longitude search to get the most results.")
    @Pattern(regexp = "^[\\-]?[\\d.]+$", message = "Invalid latitude format.")
    String latitude;

    @Schema(description = "string^[\\-]?[\\d\\.]+$, Search for properties availability around a specific longitude coordinate. We recommend that you use the radius parameter when performing a latitude/longitude search to get the most results.")
    @Pattern(regexp = "^[\\-]?[\\d.]+$", message = "Invalid longitude format.")
    String longitude;

    @Schema(description = "integer, Default: 2, Example: adults=2, The total number of adult occupants for all rooms requested. Used with children parameter to determine occupancy. Example: Two rooms, each with one adult and one child occupants, adults=2 and children=2.")
    @Min(value = 0, message = "The number of adults cannot be negative.")
    Integer adults = 2;

    @Schema(description = "integer, Default: 0, Example: children=2, The total number of child occupants for all rooms requested. Used with adults parameter to determine occupancy. Example: Two rooms, each with one adult and one child occupants, adults=2 and children=2.")
    @Min(value = 0, message = "The number of children cannot be negative.")
    Integer children = 0;

    @Schema(description = "string, Example: client_user_agent=Mozilla/5.0 (Linux; Android 10; SM-G981B) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Mobile Safari/537.36, User agent string used to detect device type when obtaining mobile rates. This string specifies the browser, version, and operating system in the device request. You need to activate mobile rates through Account Management team first.")
    String client_user_agent;

    @Schema(description = "boolean, Example: cluster_search=true, Toggles cluster search for city_id searches. Enabling this will refine the city search results to the actual city instead of surfacing neighbouring cities, for example, Toronto vs Vaughn city.")
    Boolean cluster_search = true;

    @Schema(description = "string^[a-zA-Z]{3}$, Default: 'USD', Example: currency=EUR, Requested currency for the results. ISO 4217 format.")
    @Pattern(regexp = "^[a-zA-Z]{3}$", message = "Currency must be in ISO 4217 format with 3 alphabetic characters.")
    String currency = "USD";

    @Schema(description = "string, Example: customer_nationality=BR, Pass the customer's country of passport to get the rates with regional pricing. This is a two character ISO Alpha-2 country code.")
    String customer_nationality;

    @Schema(description = "string, Example: distribution_types=PUBLIC,PRIVATE, Filter the rates by the distribution_type value of a rate. You can pass one or more comma-separated values, and the values can be PUBLIC, PRIVATE, or FLEXIBLE_BENCHMARK (case-insenstive). See the Rate Information Guide for more information about distribution types.")
    String distribution_types;

    @Schema(description = "string^[\\d\\,]+$, Example: featured_hotel_ids=700025767,70052546, PPN Hotel ID(s) to add to featured hotels in the results list")
    @Pattern(regexp = "^(\\d+(,\\d+)*)?$", message = "Featured hotel IDs must be comma-separated numeric values.")
    String featured_hotel_ids;

    @Schema(description = "boolean, Default: false, Example: free_cancellation=true, Returns the results for rates with free cancellation.")
    Boolean free_cancellation = false;

    @Schema(description = "string, Default: 'AGD,BKG,PCLN', Example: inventory_types=AGD,BKG,PCLN, Filter the rates by the inventory_type value of a rate. You can pass one or more comma-separated values, and the values can be AGD, BKG, or PCLN (case-insenstive). See the Rate Information Guide for more information about inventory types.")
    String inventory_types = "AGD,BKG,PCLN";

    @Schema(description = "string, Example: language=fr-FR, You can request translated content in a supported language. Language code should be in IETF standards with a lowercase language code (you can also add a trailing uppercase country code). See the Language Support Guide for more details.")
    String language;

    @Schema(description = "string, Example: limit=20, Limit number of hotels returned by a given number. If omitted, no hotel limiting is performed.")
    String limit;

    @Schema(description = "string or boolean or integer, Example: limit_to_country=1, Limits results to country provided. Valid Options: true or false.")
    Object limit_to_country;

    @Schema(description = "number, Enum: 1 3 4, Example: output_version=3, Default output version is 4. You can pass this parameter to request a previous version of output if needed.")
    @DecimalMin(value = "1", message = "Output version must be 1, 3, or 4.")
    @DecimalMax(value = "4", message = "Output version must be 1, 3, or 4.")
    Number output_version = 4;

    @Schema(description = "string, Example: partner_member_token=partner_123, The partner's member token is used to obtain member deal rates. Activate member deals through Account Management team to use a paratner member token.")
    String partner_member_token;

    @Schema(description = "string, Example: property_type_ids=201,213, Filter by property type ids. See the Property Type Filter Guide for more detail.")
    String property_type_ids;

    @Schema(description = "integer, Default: 25, Example: radius=25, Used to get results within a given radius (in miles) from the search location. We recommend that you use this parameter when performing a latitude/longitude search to get the most results.")
    Integer radius = 25;

    @Schema(description = "string, Example: refclickid=qwerty, A custom field that can be commonly used to track marketing attribution. This will appear in Google Analytics, and in reports with associated sales. For deep link implementations, pass this parameter with the search request to include the refclickid data into the generated external URL. Only the following characters or types of characters are supported in the refclickid value: digits, letters, space character, hyphen ( - ), back slash ( \\ ), dollar sign ($), percent sign ( % ), period ( . ), colon ( : ), caret ( ^ ), underscore ( _ ), pipe or vertical bar ( | ), tilde ( ~ )")
    String refclickid;

    @Schema(description = "integer, Default: 1, Example: rooms=1, Number of rooms required for all occupants.")
    Integer rooms = 1;

    @Schema(description = "string^[\\d\\w]+$, Example: sid=5d5aec038d76d, A unique value identifying a customer's session, to be passed into multiple endpoints. The same session ID should be passed for the duration of a customer's booking session. This value should be unique for each user session.")
    @Pattern(regexp = "^[\\d\\w]+$", message = "SID must contain alphanumeric characters only.")
    String sid;

    @Schema(description = "string^[\\w]+$, Example: sort_by=gs, Sort results by a given option/ranking. Valid Options: gs = guest_score, sr = star_rating, lp = lowest_price, hp = highest_price, ds = distance, mp = most_popular.")
    @Pattern(regexp = "^(gs|sr|lp|hp|ds|mp)$", message = "Invalid sort_by value. Valid options are: gs, sr, lp, hp, ds, mp.")
    String sort_by;

    @Schema(description = "boolean, Pass the unmapped_rooms=true in the request to include unmapped room IDs in the search results. Refer to the Mapping Room ID to Room Content Guide section here for more details.")
    Boolean unmapped_rooms;
}
