package com.example.productdelivery.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationCoordinates {
    protected double latitude;
    protected double longitude;
}
