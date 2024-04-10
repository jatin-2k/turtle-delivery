package com.example.productdelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EvaluationRequest {
    private List<LocationCoordinates> restaurantLocations;
    private List<LocationCoordinates> customerLocations;
    private LocationCoordinates deliveryPersonLocation;
}
