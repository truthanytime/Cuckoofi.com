package com.cuckoofi.pricelineservice.model.expressavailability.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BeddingData {

    @Schema(description = "Array of objects, Lists one or more areas that have beds. A bed area is a physical space in a booked room separate from other physical spaces and has one or more beds. For example, a booked room may have a living area with a pullout couch and a bedroom area with a king bed.")
    List<Area> areas;
}
