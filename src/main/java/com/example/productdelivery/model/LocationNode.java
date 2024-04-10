package com.example.productdelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocationNode extends LocationCoordinates{
    LocationType locationType;

    public LocationNode(LocationCoordinates locationCoordinates, LocationType locationType) {
        super(locationCoordinates.latitude, locationCoordinates.longitude);
        this.locationType = locationType;
    }
}
