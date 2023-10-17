package com.cuckoofi.pricelineservice.model.hoteldetails.request;

import com.cuckoofi.commonclientlibs.constant.PricelineConstant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class HotelDetailsRequest implements Serializable {

    @Schema(description = "Default: 'json2', Enum: 'xml' 'json' 'json2', The format of your request. We recommend requesting json2 format response.")
    String format = "json2";

    @Schema(description = "string <date>, Example: check_in=2023-09-19, Check In Date (YYYY-MM-DD or MM/DD/YYYY)")
    @NotNull(message = PricelineConstant.REQUIRED_HOTEL_ID)
    String check_in;

    @Schema(description = "string <date>, Example: check_out=2023-09-20, Check Out Date (YYYY-MM-DD or MM/DD/YYYY)")
    @NotNull(message = PricelineConstant.REQUIRED_HOTEL_ID)
    String check_out;

    @Schema(description = "string^[A-Z]{3}$, Default: 'USD', Example: currency=EUR, Requested currency for the results. ISO 4217 format.")
    String currency = "USD";

    @Schema(description = "string or boolean or integer, Example: guest_score_breakdown=true, Toggle guest score breakdown on and off. Valid Options: True or False")
    Boolean guest_score_breakdown = true;

    @Schema(description = "string or boolean or integer, Example: id_lookup=true, Allows non-PPN Hotel IDs to be searched in the database. Valid Options: True or False")
    Boolean id_lookup = true;

    @Schema(description = "string^(small|medium|large), Example: image_size=medium, The size of the image returned. Valid Options: small (60px), medium(300 to 312px) or large(500 to 800px).")
    String image_size = "medium";

    @Schema(description = "string or boolean or integer, Example: important_info=true, Toggles important info. Important info is extra details regarding the specified hotel. By default, important info is excluded in the response. Valid Options: 0 = Off, 1 = On.")
    Boolean important_info = true;

    @Schema(description = "string or boolean or integer, Example: nearby=true, Toggles nearby data. Nearby data is extra information regarding the specified hotel's location. Nearby data can include city_data, airport_data, and poi_category_data. By default, nearby data is excluded from the response. To include nearby data, set nearby to 1. To include city_data within nearby data, include a city_limit parameter to a value greater than 1. To include airport_data within nearby data, include a airport_limit parameter to a value greater than 1. To include poi_category_data within nearby data, include a poi_limit parameter to a value greater than 1.")
    Boolean nearby = true;

    @Schema(description = "string or boolean or integer, Example: photos=true, Toggles important info. Toggles photo data. By default, hotel photo data is excluded in the response. Valid Options: 0 = Off, 1 = On.")
    Boolean photos = true;

    @Schema(description = "string^[0-9,]+$, Example: plugins=1,2, Provides extra information regarding plugins for the specified hotel. To include specific plugin information, include the ID of the plugin as a comma separated value to the plugins parameter.")
    String plugins = null;

    @Schema(description = "string or boolean or integer, Example: promo=true, Toggle the hotel's promo data on and off. By default, promo data is excluded in the response. To include promo data in the response, set promo to 1.")
    Boolean promo = true;

    @Schema(description = "string or boolean or integer, Example: recent=true, Toggle recent sales. Valid Options: True, False")
    Boolean recent = true;

    @Schema(description = "string or boolean or integer, Example: reviews=true, Toggle hotel review data. By default, review data is excluded in the response. Valid Options: 0 = False, 1 = On.")
    Boolean reviews = true;

    @Schema(description = "string^[\\d\\w]+$, Example: sid=5d5ec7f34cfbe, A unique value identifying a customer's session, to be passed into multiple endpoints. The same session ID should be passed for the duration of a customer's booking session. This value should be unique for each user session.")
    String sid = null;

    @Schema(description = "string or boolean or integer, Example: videos=true, Toggle videos. Valid Options: True or False")
    Boolean videos = true;
}
