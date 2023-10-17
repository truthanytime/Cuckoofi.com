package com.cuckoofi.pricelineservice.model.expressavailability.response.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Area {

    @Schema(description = "string, A list of areas where one or more beds are in. For example, beds can be in a separate BEDROOM, or a sofa bed might be part of a shared LIVING_ROOM.")
    String type;

    @Schema(description = "Array of objects, A bed group is one of the potential combinations of bedding configuration in an area. For example, a room that is sold to sleep 2 occupants may have either a bed group with 1 Queen bed OR, a bed group with 1 Twin bed AND 1 Single bed. The guests will get one of the available bed configurations from the possible combinations. The choice of configuration is at the discretion of the property. But the guests will receive all of the beds listed under a specific bed group.")
    List<BedGroup> bed_groups;
}
