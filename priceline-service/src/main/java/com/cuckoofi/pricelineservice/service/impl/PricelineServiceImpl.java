package com.cuckoofi.pricelineservice.service.impl;

import com.cuckoofi.pricelineservice.configuration.PricelineConfig;
import com.cuckoofi.pricelineservice.model.autosuggestv2.request.AutoSuggestS2Request;
import com.cuckoofi.pricelineservice.model.autosuggestv2.response.format.AutoSuggestV2ApiResponse;
import com.cuckoofi.pricelineservice.model.expressavailability.request.ExpressAvailabilityRequest;
import com.cuckoofi.pricelineservice.model.expressavailability.response.format.ExpressAvailabilityApiResponse;
import com.cuckoofi.pricelineservice.service.PricelineService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class PricelineServiceImpl implements PricelineService {

    @Autowired
    private PricelineConfig pricelineConfig;

    @Override
    public Object getAutoSuggestV2(AutoSuggestS2Request autoSuggestS2Request)
            throws UnirestException, JsonProcessingException {

        // Build the URL with query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(pricelineConfig.getBaseUrl() + "/api/hotel/getAutoSuggestV2")
                .queryParam("refid", pricelineConfig.getRefId())
                .queryParam("api_key", pricelineConfig.getApiKey());

        if (autoSuggestS2Request.getFormat() != null)
            builder.queryParam("format", autoSuggestS2Request.getFormat());

        if (autoSuggestS2Request.getString() != null)
            builder.queryParam("string", autoSuggestS2Request.getString());

        if (autoSuggestS2Request.getCombine_regions() != null)
            builder.queryParam("combine_regions", autoSuggestS2Request.getCombine_regions());

        if (autoSuggestS2Request.getGet_airports() != null)
            builder.queryParam("get_airports", autoSuggestS2Request.getGet_airports());

        if (autoSuggestS2Request.getGet_cities() != null)
            builder.queryParam("get_cities", autoSuggestS2Request.getGet_cities());

        if (autoSuggestS2Request.getGet_regions() != null)
            builder.queryParam("get_regions", autoSuggestS2Request.getGet_regions());

        if (autoSuggestS2Request.getGet_pois() != null)
            builder.queryParam("get_pois", autoSuggestS2Request.getGet_pois());

        if (autoSuggestS2Request.getGet_hotels() != null)
            builder.queryParam("get_hotels", autoSuggestS2Request.getGet_hotels());

        if (autoSuggestS2Request.getMax_results() != null)
            builder.queryParam("max_results", autoSuggestS2Request.getMax_results());

        if (autoSuggestS2Request.getShow_all_cities() != null)
            builder.queryParam("show_all_cities", autoSuggestS2Request.getShow_all_cities());

        if (autoSuggestS2Request.getSpellcheck() != null)
            builder.queryParam("spellcheck", autoSuggestS2Request.getSpellcheck());

        String url = builder.toUriString();

        // Call priceline API
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get(url).asString();

        // Parsing response
        ObjectMapper objectMapper = new ObjectMapper();
        AutoSuggestV2ApiResponse autoSuggestV2ApiResponse = objectMapper.readValue(response.getBody(), AutoSuggestV2ApiResponse.class);

        if( autoSuggestV2ApiResponse.getGetHotelAutoSuggestV2().getResults() == null ){
            Object errorObject = objectMapper.readValue(response.getBody(), Object.class);
            autoSuggestV2ApiResponse.getGetHotelAutoSuggestV2().setError(errorObject);
        }

        return autoSuggestV2ApiResponse;
    }

    @Override
    public Object getExpressAvailability(ExpressAvailabilityRequest expressAvailabilityRequest)
            throws UnirestException, JsonProcessingException {

        // Use UriComponentsBuilder to build the URL with query parameters
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(pricelineConfig.getBaseUrl() + "/api/hotel/getExpress.Availability")
                .queryParam("refid", pricelineConfig.getRefId())
                .queryParam("api_key", pricelineConfig.getApiKey());

        if( expressAvailabilityRequest.getFormat() != null )
            builder.queryParam("format", expressAvailabilityRequest.getFormat());

        if( expressAvailabilityRequest.getCheck_in() != null )
            builder.queryParam("check_in", expressAvailabilityRequest.getCheck_in());

        if( expressAvailabilityRequest.getCheck_out() != null )
            builder.queryParam("check_out", expressAvailabilityRequest.getCheck_out());

        if( expressAvailabilityRequest.getCity_id() != null )
            builder.queryParam("city_id", expressAvailabilityRequest.getCity_id());

        if( expressAvailabilityRequest.getAirport_code() != null )
            builder.queryParam("airport_code", expressAvailabilityRequest.getAirport_code());

        if( expressAvailabilityRequest.getHotel_ids() != null )
            builder.queryParam("hotel_ids", expressAvailabilityRequest.getHotel_ids());

        if( expressAvailabilityRequest.getLatitude() != null )
            builder.queryParam("latitude", expressAvailabilityRequest.getLatitude());

        if( expressAvailabilityRequest.getLongitude() != null )
            builder.queryParam("longitude", expressAvailabilityRequest.getLongitude());

        if( expressAvailabilityRequest.getAdults() != null )
            builder.queryParam("adults", expressAvailabilityRequest.getAdults());

        if( expressAvailabilityRequest.getChildren() != null )
            builder.queryParam("children", expressAvailabilityRequest.getChildren());

        if( expressAvailabilityRequest.getClient_user_agent() != null )
            builder.queryParam("client_user_agent", expressAvailabilityRequest.getClient_user_agent());

        if( expressAvailabilityRequest.getCluster_search() != null )
            builder.queryParam("cluster_search", expressAvailabilityRequest.getCluster_search());

        if( expressAvailabilityRequest.getCurrency() != null )
            builder.queryParam("currency", expressAvailabilityRequest.getCurrency());

        if( expressAvailabilityRequest.getCustomer_nationality() != null )
            builder.queryParam("customer_nationality", expressAvailabilityRequest.getCustomer_nationality());

        if( expressAvailabilityRequest.getDistribution_types() != null )
            builder.queryParam("distribution_types", expressAvailabilityRequest.getDistribution_types());

        if( expressAvailabilityRequest.getFeatured_hotel_ids() != null )
            builder.queryParam("featured_hotel_ids", expressAvailabilityRequest.getFeatured_hotel_ids());

        if( expressAvailabilityRequest.getFree_cancellation() != null )
            builder.queryParam("free_cancellation", expressAvailabilityRequest.getFree_cancellation());

        if( expressAvailabilityRequest.getInventory_types() != null )
            builder.queryParam("inventory_types", expressAvailabilityRequest.getInventory_types());

        if( expressAvailabilityRequest.getLanguage() != null )
            builder.queryParam("language", expressAvailabilityRequest.getLanguage());

        if( expressAvailabilityRequest.getLimit() != null )
            builder.queryParam("limit", expressAvailabilityRequest.getLimit());

        if( expressAvailabilityRequest.getLimit_to_country() != null )
            builder.queryParam("limit_to_country", expressAvailabilityRequest.getLimit_to_country());

        if( expressAvailabilityRequest.getOutput_version() != null )
            builder.queryParam("output_version", expressAvailabilityRequest.getOutput_version());

        if( expressAvailabilityRequest.getPartner_member_token() != null )
            builder.queryParam("partner_member_token", expressAvailabilityRequest.getPartner_member_token());

        if( expressAvailabilityRequest.getProperty_type_ids() != null )
            builder.queryParam("property_type_ids", expressAvailabilityRequest.getProperty_type_ids());

        if( expressAvailabilityRequest.getRadius() != null )
            builder.queryParam("radius", expressAvailabilityRequest.getRadius());

        if( expressAvailabilityRequest.getRefclickid() != null )
            builder.queryParam("refclickid", expressAvailabilityRequest.getRefclickid());

        if( expressAvailabilityRequest.getRooms() != null )
            builder.queryParam("rooms", expressAvailabilityRequest.getRooms());

        if( expressAvailabilityRequest.getSid() != null )
            builder.queryParam("sid", expressAvailabilityRequest.getSid());

        if( expressAvailabilityRequest.getSort_by() != null )
            builder.queryParam("sort_by", expressAvailabilityRequest.getSort_by());

        if( expressAvailabilityRequest.getUnmapped_rooms() != null )
            builder.queryParam("unmapped_rooms", expressAvailabilityRequest.getUnmapped_rooms());

        String url = builder.toUriString();

        // Call priceline API
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get(url).asString();

        // Parsing response
        ObjectMapper objectMapper = new ObjectMapper();
        ExpressAvailabilityApiResponse expressAvailabilityApiResponse =
                objectMapper.readValue(response.getBody(), ExpressAvailabilityApiResponse.class);

        if( expressAvailabilityApiResponse.getExpressAvailabilityResponse().getResults() == null ){
            Object errorObject = objectMapper.readValue(response.getBody(), Object.class);
            expressAvailabilityApiResponse.getExpressAvailabilityResponse().setError(errorObject);
        }

        return expressAvailabilityApiResponse;
    }
}
