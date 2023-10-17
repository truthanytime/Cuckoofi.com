package com.cuckoofi.pricelineservice.model.autosuggestv2.request;

import com.cuckoofi.commonclientlibs.constant.PricelineConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.io.Serializable;

@Data
public class AutoSuggestS2Request implements Serializable {

    @Schema(description = "Default: 'json2', Enum: 'xml' 'json' 'json2', The format of your request. We recommend requesting json2 format response.")
    @Pattern(regexp = "^(xml|json|json2)$", message = "Invalid format. Use 'xml', 'json', or 'json2'.")
    String format = "json2";

    @Schema(description = "string, Example: string=New York, Search string that will enable a list of selection to be listed to the traveller.")
    @NotNull(message = PricelineConstant.REQUIRED_STRING)
    String string;

    @Schema(description = "string or boolean or integer, Example: combine_regions=true, If combine_regions is true, there will be regions data in the city data array that is returned.")
    Object combine_regions;

    @Schema(description = "string or boolean or integer, Example: get_airports=true, Include airports in search results.")
    Object get_airports;

    @Schema(description = "string or boolean or integer, Example: get_cities=true, Include cities in search results.")
    Object get_cities;

    @Schema(description = "string or boolean or integer, Example: get_regions=true, Include Regions in search results.")
    Object get_regions;

    @Schema(description = "string or boolean or integer, Example: get_pois=true, Include Points of Interest in search results.")
    Object get_pois;

    @Schema(description = "string or boolean or integer, Example: get_hotels=true, Include hotels in search results.")
    Object get_hotels;

    @Schema(description = "any, Example: max_results=10, Number passed is the maximum number of results returned.")
    Object max_results;

    @Schema(description = "string^[A-Za-z]+$, Default: 'desc', Example: order=desc, Method of ordering the results of the search.")
    @Pattern(regexp = "^(desc|asc)$", message = "Invalid order value. Valid options are: desc, asc.")
    String order = "desc";

    @Schema(description = "string or boolean or integer, Example: show_all_cities=true, Will filter out cities with no hotels. Valid Options: False = filter out cities without hotels, True = show cities with and without hotels.")
    Object show_all_cities;

    @Schema(description = "string, Default: 'rank', Enum: 'rank' 'name', Example: sort=name, Method of sorting the results.")
    String sort = "rank";

    @Schema(description = "string or boolean or integer, Example: spellcheck=true, Apply strict spell checking when matching the search string for results.")
    Object spellcheck;
}
