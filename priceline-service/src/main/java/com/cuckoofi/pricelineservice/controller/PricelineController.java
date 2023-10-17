package com.cuckoofi.pricelineservice.controller;

import com.cuckoofi.commonclientlibs.constant.PricelineConstant;
import com.cuckoofi.commonclientlibs.utils.Response;
import com.cuckoofi.pricelineservice.configuration.PricelineConfig;
import com.cuckoofi.pricelineservice.model.autosuggestv2.request.AutoSuggestS2Request;
import com.cuckoofi.pricelineservice.model.autosuggestv2.response.format.AutoSuggestV2ApiResponse;
import com.cuckoofi.pricelineservice.model.expressavailability.request.ExpressAvailabilityRequest;
import com.cuckoofi.pricelineservice.model.expressavailability.response.format.ExpressAvailabilityApiResponse;
import com.cuckoofi.pricelineservice.model.hoteldetails.request.HotelDetailsRequest;
import com.cuckoofi.pricelineservice.model.hoteldetails.response.format.HotelDetailsResponse;
import com.cuckoofi.pricelineservice.service.PricelineService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping
public class PricelineController {

    private static final Logger logger = LoggerFactory.getLogger(PricelineController.class);

    @Autowired
    PricelineService pricelineService;

    @Autowired
    private PricelineConfig pricelineConfig;

    @GetMapping("/getExpress.Availability")
    @Operation(summary = "This endpoint is similar to Express.Results but is more efficient and faster by returning a pricing summary (single rate) per property, perfect for displaying a search results-style page.")
    public ResponseEntity<Response<ExpressAvailabilityApiResponse>> getExpressAvailability(
            @Schema(description = "Default: 'json2', Enum: 'xml' 'json' 'json2', The format of your request. We recommend requesting json2 format response.")
            @Pattern(regexp = "^(xml|json|json2)$", message = "Invalid format. Use 'xml', 'json', or 'json2'.")
            @RequestParam(name = "format", defaultValue = "json2", required = false)
                    String format,

            @Schema(description = "string <date>, Example: check_in=2023-09-19, Check In Date (YYYY-MM-DD or MM/DD/YYYY)")
            @NotNull(message = "Check-in date is required")
            @RequestParam(name = "check_in")
                    String check_in,

            @Schema(description = "string <date>, Example: check_out=2023-09-20, Check Out Date (YYYY-MM-DD or MM/DD/YYYY)")
            @NotNull(message = "Check-out date is required")
            @RequestParam(name = "check_out")
                    String check_out,

            @Schema(description = "string^[0-9]+$, Accepts a single PPN City ID.")
            @Pattern(regexp = "^[0-9]+$", message = "City ID must contain only numeric digits.")
            @RequestParam(name = "city_id", required = false)
                    String city_id,

            @Schema(description = "string^[a-zA-Z]{3}$, Accepts a 3-character IATA airport code.")
            @Pattern(regexp = "^[a-zA-Z]{3}$", message = "Invalid airport code format. Use a 3-character IATA code.")
            @RequestParam(name = "airport_code", required = false)
                    String airport_code,

            @Schema(description = "string^[\\d\\,]+$, Comma separated string of PPN hotel ids.")
            @Pattern(regexp = "^[\\d,]+$", message = "Invalid hotel IDs format.")
            @RequestParam(name = "hotel_ids", required = false)
                    String hotel_ids,

            @Schema(description = "string^[\\-]?[\\d\\.]+$, Search for properties availability around a specific latitude coordinate. We recommend that you use the radius parameter when performing a latitude/longitude search to get the most results.")
            @Pattern(regexp = "^[\\-]?[\\d.]+$", message = "Invalid latitude format.")
            @RequestParam(name = "latitude", required = false)
                    String latitude,

            @Schema(description = "string^[\\-]?[\\d\\.]+$, Search for properties availability around a specific longitude coordinate. We recommend that you use the radius parameter when performing a latitude/longitude search to get the most results.")
            @Pattern(regexp = "^[\\-]?[\\d.]+$", message = "Invalid longitude format.")
            @RequestParam(name = "longitude", required = false)
                    String longitude,

            @Schema(description = "integer, Default: 2, Example: adults=2, The total number of adult occupants for all rooms requested. Used with children parameter to determine occupancy. Example: Two rooms, each with one adult and one child occupants, adults=2 and children=2.")
            @Min(value = 0, message = "The number of adults cannot be negative.")
            @RequestParam(name = "adults", defaultValue = "2", required = false)
                    Integer adults,

            @Schema(description = "integer, Default: 0, Example: children=2, The total number of child occupants for all rooms requested. Used with adults parameter to determine occupancy. Example: Two rooms, each with one adult and one child occupants, adults=2 and children=2.")
            @Min(value = 0, message = "The number of children cannot be negative.")
            @RequestParam(name = "children", defaultValue = "0", required = false)
                    Integer children,

            @Schema(description = "string, Example: client_user_agent=Mozilla/5.0 (Linux; Android 10; SM-G981B) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Mobile Safari/537.36, User agent string used to detect device type when obtaining mobile rates. This string specifies the browser, version, and operating system in the device request. You need to activate mobile rates through Account Management team first.")
            @RequestParam(name = "client_user_agent", required = false)
                    String client_user_agent,

            @Schema(description = "boolean, Example: cluster_search=true, Toggles cluster search for city_id searches. Enabling this will refine the city search results to the actual city instead of surfacing neighbouring cities, for example, Toronto vs Vaughn city.")
            @RequestParam(name = "cluster_search", defaultValue = "true", required = false)
                    Boolean cluster_search,

            @Schema(description = "string^[a-zA-Z]{3}$, Default: 'USD', Example: currency=EUR, Requested currency for the results. ISO 4217 format.")
            @Pattern(regexp = "^[a-zA-Z]{3}$", message = "Currency must be in ISO 4217 format with 3 alphabetic characters.")
            @RequestParam(name = "currency", defaultValue = "USD", required = false)
                    String currency,

            @Schema(description = "string, Example: customer_nationality=BR, Pass the customer's country of passport to get the rates with regional pricing. This is a two character ISO Alpha-2 country code.")
            @RequestParam(name = "customer_nationality", required = false)
                    String customer_nationality,

            @Schema(description = "string, Example: distribution_types=PUBLIC,PRIVATE, Filter the rates by the distribution_type value of a rate. You can pass one or more comma-separated values, and the values can be PUBLIC, PRIVATE, or FLEXIBLE_BENCHMARK (case-insenstive). See the Rate Information Guide for more information about distribution types.")
            @RequestParam(name = "distribution_types", required = false)
                    String distribution_types,

            @Schema(description = "string^[\\d\\,]+$, Example: featured_hotel_ids=700025767,70052546, PPN Hotel ID(s) to add to featured hotels in the results list")
            @Pattern(regexp = "^(\\d+(,\\d+)*)?$", message = "Featured hotel IDs must be comma-separated numeric values.")
            @RequestParam(name = "featured_hotel_ids", required = false)
                    String featured_hotel_ids,

            @Schema(description = "boolean, Default: false, Example: free_cancellation=true, Returns the results for rates with free cancellation.")
            @RequestParam(name = "free_cancellation", defaultValue = "false", required = false)
                    Boolean free_cancellation,

            @Schema(description = "string, Default: 'AGD,BKG,PCLN', Example: inventory_types=AGD,BKG,PCLN, Filter the rates by the inventory_type value of a rate. You can pass one or more comma-separated values, and the values can be AGD, BKG, or PCLN (case-insenstive). See the Rate Information Guide for more information about inventory types.")
            @RequestParam(name = "inventory_types", defaultValue = "AGD,BKG,PCLN", required = false)
                    String inventory_types,

            @Schema(description = "string, Example: language=fr-FR, You can request translated content in a supported language. Language code should be in IETF standards with a lowercase language code (you can also add a trailing uppercase country code). See the Language Support Guide for more details.")
            @RequestParam(name = "language", required = false)
                    String language,

            @Schema(description = "string, Example: limit=20, Limit number of hotels returned by a given number. If omitted, no hotel limiting is performed.")
            @RequestParam(name = "limit", required = false)
                    String limit,

            @Schema(description = "string or boolean or integer, Example: limit_to_country=1, Limits results to country provided. Valid Options: true or false.")
            @RequestParam(name = "limit_to_country", required = false)
                    Object limit_to_country,

            @Schema(description = "number, Enum: 1 3 4, Example: output_version=3, Default output version is 4. You can pass this parameter to request a previous version of output if needed.")
            @DecimalMin(value = "1", message = "Output version must be 1, 3, or 4.")
            @DecimalMax(value = "4", message = "Output version must be 1, 3, or 4.")
            @RequestParam(name = "output_version", defaultValue = "4", required = false)
                    Number output_version,

            @Schema(description = "string, Example: partner_member_token=partner_123, The partner's member token is used to obtain member deal rates. Activate member deals through Account Management team to use a paratner member token.")
            @RequestParam(name = "partner_member_token", required = false)
                    String partner_member_token,

            @Schema(description = "string, Example: property_type_ids=201,213, Filter by property type ids. See the Property Type Filter Guide for more detail.")
            @RequestParam(name = "property_type_ids", required = false)
                    String property_type_ids,

            @Schema(description = "integer, Default: 25, Example: radius=25, Used to get results within a given radius (in miles) from the search location. We recommend that you use this parameter when performing a latitude/longitude search to get the most results.")
            @RequestParam(name = "radius", defaultValue = "25", required = false)
                    Integer radius,

            @Schema(description = "string, Example: refclickid=qwerty, A custom field that can be commonly used to track marketing attribution. This will appear in Google Analytics, and in reports with associated sales. For deep link implementations, pass this parameter with the search request to include the refclickid data into the generated external URL. Only the following characters or types of characters are supported in the refclickid value: digits, letters, space character, hyphen ( - ), back slash ( \\ ), dollar sign ($), percent sign ( % ), period ( . ), colon ( : ), caret ( ^ ), underscore ( _ ), pipe or vertical bar ( | ), tilde ( ~ )")
            @RequestParam(name = "refclickid", required = false)
                    String refclickid,

            @Schema(description = "integer, Default: 1, Example: rooms=1, Number of rooms required for all occupants.")
            @RequestParam(name = "rooms", defaultValue = "1", required = false)
                    Integer rooms,

            @Schema(description = "string^[\\d\\w]+$, Example: sid=5d5aec038d76d, A unique value identifying a customer's session, to be passed into multiple endpoints. The same session ID should be passed for the duration of a customer's booking session. This value should be unique for each user session.")
            @Pattern(regexp = "^[\\d\\w]+$", message = "SID must contain alphanumeric characters only.")
            @RequestParam(name = "sid", required = false)
                    String sid,

            @Schema(description = "string^[\\w]+$, Example: sort_by=gs, Sort results by a given option/ranking. Valid Options: gs = guest_score, sr = star_rating, lp = lowest_price, hp = highest_price, ds = distance, mp = most_popular.")
            @Pattern(regexp = "^(gs|sr|lp|hp|ds|mp)$", message = "Invalid sort_by value. Valid options are: gs, sr, lp, hp, ds, mp.")
            @RequestParam(name = "sort_by", required = false)
                    String sort_by,

            @Schema(description = "boolean, Pass the unmapped_rooms=true in the request to include unmapped room IDs in the search results. Refer to the Mapping Room ID to Room Content Guide section here for more details.")
            @RequestParam(name = "unmapped_rooms", required = false)
                    Boolean unmapped_rooms) {
        try {
            logger.info("getExpressAvailability method called");

            // Create an AutoSuggestS2Request object and set its properties
            ExpressAvailabilityRequest expressAvailabilityRequest = new ExpressAvailabilityRequest();
            expressAvailabilityRequest.setFormat(format);
            expressAvailabilityRequest.setCheck_in(check_in);
            expressAvailabilityRequest.setCheck_out(check_out);
            expressAvailabilityRequest.setCity_id(city_id);
            expressAvailabilityRequest.setAirport_code(airport_code);
            expressAvailabilityRequest.setHotel_ids(hotel_ids);
            expressAvailabilityRequest.setLatitude(latitude);
            expressAvailabilityRequest.setLongitude(longitude);
            expressAvailabilityRequest.setAdults(adults);
            expressAvailabilityRequest.setChildren(children);
            expressAvailabilityRequest.setClient_user_agent(client_user_agent);
            expressAvailabilityRequest.setCluster_search(cluster_search);
            expressAvailabilityRequest.setCurrency(currency);
            expressAvailabilityRequest.setCustomer_nationality(customer_nationality);
            expressAvailabilityRequest.setDistribution_types(distribution_types);
            expressAvailabilityRequest.setFeatured_hotel_ids(featured_hotel_ids);
            expressAvailabilityRequest.setFree_cancellation(free_cancellation);
            expressAvailabilityRequest.setInventory_types(inventory_types);
            expressAvailabilityRequest.setLanguage(language);
            expressAvailabilityRequest.setLimit(limit);
            expressAvailabilityRequest.setLimit_to_country(limit_to_country);
            expressAvailabilityRequest.setOutput_version(output_version);
            expressAvailabilityRequest.setPartner_member_token(partner_member_token);
            expressAvailabilityRequest.setProperty_type_ids(property_type_ids);
            expressAvailabilityRequest.setRadius(radius);
            expressAvailabilityRequest.setRefclickid(refclickid);
            expressAvailabilityRequest.setRooms(rooms);
            expressAvailabilityRequest.setSid(sid);
            expressAvailabilityRequest.setSort_by(sort_by);
            expressAvailabilityRequest.setUnmapped_rooms(unmapped_rooms);

            Object resp = pricelineService.getExpressAvailability( expressAvailabilityRequest );
            if( resp.getClass() == ExpressAvailabilityApiResponse.class ){
                return ResponseEntity.ok(new Response<>(
                        HttpStatus.OK.value(),
                        true,
                        "The Express.Availability data",
                        (ExpressAvailabilityApiResponse)resp
                ));
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new Response<>(
                                HttpStatus.BAD_REQUEST.value(),
                                false,
                                resp.toString()
                        ));
            }
        }
        catch (Exception e) {
            logger.error("error happened in getExpressAvailability method");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new Response<>(
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            false,
                            e.getMessage()
                    ));
        }
    }

    @GetMapping("/getHotelDetails")
    @Operation(summary = "Retrieve hotel details dynamically during the booking path.")
    public ResponseEntity<Response<HotelDetailsResponse>> getHotelDetails(
            @Valid HotelDetailsRequest hotelDetailsRequest){
        try {
            logger.info("getHotelDetails method called");

            // Access the properties of the requestDTO object
            String refId = pricelineConfig.getRefId();
            String apiKey = pricelineConfig.getApiKey();
            String format = hotelDetailsRequest.getFormat();
            String checkIn = hotelDetailsRequest.getCheck_in();
            String checkOut = hotelDetailsRequest.getCheck_out();
            String currency = hotelDetailsRequest.getCurrency();

            // Make the GET request using the query string or other parameters as needed
            String queryString = "refid=" + refId +
                    "&api_key=" + apiKey +
                    "&format=" + format +
                    "&check_in=" + checkIn +
                    "&check_out=" + checkOut +
                    "&currency=" + currency;

            // Create a RestTemplate and make the Priceline API request
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<HotelDetailsResponse> response = restTemplate.getForEntity(
                    pricelineConfig.getBaseUrl() + "/api/hotel/getHotelDetails"+ "?" + queryString, HotelDetailsResponse.class);

            // Response with response data from Priceline
            return ResponseEntity.ok(new Response<>(
                    HttpStatus.OK.value(),
                    true,
                    "The hotel details data",
                    response.getBody()
            ));
        } catch (Exception e) {
            logger.error("error happened in getHotelDetails method");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new Response<>(
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            false,
                            e.getMessage()
                    ));
        }
    }

    @GetMapping("/getAutoSuggestV2")
    @Operation(summary = "AutoSuggestV2, This API will provide a list of possible cities and hotels for a given search string using the endpoint getAutoSuggestV2.")
    public ResponseEntity<Response<AutoSuggestV2ApiResponse>> getAutoSuggestV2(

            @Schema(description = "Default: 'json2', Enum: 'xml' 'json' 'json2', The format of your request. We recommend requesting json2 format response.")
            @Pattern(regexp = "^(xml|json|json2)$", message = "Invalid format. Use 'xml', 'json', or 'json2'.")
            @RequestParam(name = "format", defaultValue = "json2", required = false)
                    String format,

            @Schema(description = "string, Example: string=New York, Search string that will enable a list of selection to be listed to the traveller.")
            @NotNull(message = PricelineConstant.REQUIRED_STRING)
            @RequestParam(name = "string")
                    String string,

            @Schema(description = "string or boolean or integer, Example: combine_regions=true, If combine_regions is true, there will be regions data in the city data array that is returned.")
            @RequestParam(name = "combine_regions", required = false)
                    Object combine_regions,

            @Schema(description = "string or boolean or integer, Example: get_airports=true, Include airports in search results.")
            @RequestParam(name = "get_airports", required = false)
                    Object get_airports,

            @Schema(description = "string or boolean or integer, Example: get_cities=true, Include cities in search results.")
            @RequestParam(name = "get_cities", required = false)
                    Object get_cities,

            @Schema(description = "string or boolean or integer, Example: get_regions=true, Include Regions in search results.")
            @RequestParam(name = "get_regions", required = false)
                    Object get_regions,

            @Schema(description = "string or boolean or integer, Example: get_pois=true, Include Points of Interest in search results.")
            @RequestParam(name = "get_pois", required = false)
                    Object get_pois,

            @Schema(description = "string or boolean or integer, Example: get_hotels=true, Include hotels in search results.")
            @RequestParam(name = "get_hotels", required = false)
                    Object get_hotels,

            @Schema(description = "any, Example: max_results=10, Number passed is the maximum number of results returned.")
            @RequestParam(name = "max_results", required = false)
                    Object max_results,

            @Schema(description = "string^[A-Za-z]+$, Default: 'desc', Example: order=desc, Method of ordering the results of the search.")
            @Pattern(regexp = "^(desc|asc)$", message = "Invalid order value. Valid options are: desc, asc.")
            @RequestParam(name = "desc", defaultValue = "desc", required = false)
                    String order,

            @Schema(description = "string or boolean or integer, Example: show_all_cities=true, Will filter out cities with no hotels. Valid Options: False = filter out cities without hotels, True = show cities with and without hotels.")
            @RequestParam(name = "show_all_cities", required = false)
                    Object show_all_cities,

            @Schema(description = "string, Default: 'rank', Enum: 'rank' 'name', Example: sort=name, Method of sorting the results.")
            @RequestParam(name = "rank", defaultValue = "rank", required = false)
                    String sort,

            @Schema(description = "string or boolean or integer, Example: spellcheck=true, Apply strict spell checking when matching the search string for results.")
            @RequestParam(name = "spellcheck", required = false)
                    Object spellcheck) {
        try {
            logger.info("getAutoSuggestV2 method called");

            // Create an AutoSuggestS2Request object and set its properties
            AutoSuggestS2Request autoSuggestS2Request = new AutoSuggestS2Request();
            autoSuggestS2Request.setFormat(format);
            autoSuggestS2Request.setString(string);
            autoSuggestS2Request.setCombine_regions(combine_regions);
            autoSuggestS2Request.setGet_airports(get_airports);
            autoSuggestS2Request.setGet_cities(get_cities);
            autoSuggestS2Request.setGet_regions(get_regions);
            autoSuggestS2Request.setGet_pois(get_pois);
            autoSuggestS2Request.setGet_hotels(get_hotels);
            autoSuggestS2Request.setMax_results(max_results);
            autoSuggestS2Request.setOrder(order);
            autoSuggestS2Request.setShow_all_cities(show_all_cities);
            autoSuggestS2Request.setSort(sort);
            autoSuggestS2Request.setSpellcheck(spellcheck);

            Object resp = pricelineService.getAutoSuggestV2( autoSuggestS2Request );
            if( resp.getClass() == AutoSuggestV2ApiResponse.class ){
                return ResponseEntity.ok(new Response<>(
                        HttpStatus.OK.value(),
                        true,
                        "The AutoSuggestV2 data",
                        (AutoSuggestV2ApiResponse)resp
                ));
            }
            else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new Response<>(
                                HttpStatus.BAD_REQUEST.value(),
                                false,
                                resp.toString()
                        ));
            }
        }
        catch (Exception e) {
            logger.error("error happened in getAutoSuggestV2 method");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new Response<>(
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            false,
                            e.getMessage()
                    ));
        }
    }

    @PostMapping("/pricelineTest")
    public ResponseEntity<Response<Void>> pricelineTest() {
        try {
            logger.info("pricelineTest method called");
            return ResponseEntity.ok(new Response<>(
                    HttpStatus.OK.value(),
                    true,
                    "ok"
            ));
        } catch (Exception e) {
            logger.error("error happened in pricelineTest method");
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new Response<>(
                            HttpStatus.INTERNAL_SERVER_ERROR.value(),
                            false,
                            e.getMessage()
                    ));
        }
    }
}
