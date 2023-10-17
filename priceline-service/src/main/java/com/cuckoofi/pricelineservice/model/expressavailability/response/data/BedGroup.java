package com.cuckoofi.pricelineservice.model.expressavailability.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BedGroup {

    @Schema(description = "Array of objects, One or more beds of the same type. For example, 2 QUEEN-sized bed types may be listed.")
    List<Bed> geds;
}
