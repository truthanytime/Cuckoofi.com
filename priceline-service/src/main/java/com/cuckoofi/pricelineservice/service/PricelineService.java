package com.cuckoofi.pricelineservice.service;

import com.cuckoofi.pricelineservice.model.autosuggestv2.request.AutoSuggestS2Request;
import com.cuckoofi.pricelineservice.model.expressavailability.request.ExpressAvailabilityRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.exceptions.UnirestException;

public interface PricelineService {

    Object getAutoSuggestV2( AutoSuggestS2Request autoSuggestS2Request ) throws UnirestException, JsonProcessingException;

    Object getExpressAvailability( ExpressAvailabilityRequest expressAvailabilityRequest ) throws UnirestException, JsonProcessingException;
}
