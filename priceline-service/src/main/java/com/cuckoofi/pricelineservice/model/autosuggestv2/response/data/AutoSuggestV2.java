package com.cuckoofi.pricelineservice.model.autosuggestv2.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AutoSuggestV2 implements Serializable {

    @Schema(description = "Array of objects, Airport results list.")
    List<Airport> airports;

    @Schema(description = "Array of objects, List of cities that matches the search string.")
    List<City> cities;

    @Schema(description = "Array of objects, List of hotels that matches the search string.")
    List<Hotel> hotels;

    @Schema(description = "Array of objects, Points of interest that matches the search string.")
    List<Poi> pois;

    @Schema(description = "Array of objects, Regions that matches the search string.")
    List<Region> regions;
}
