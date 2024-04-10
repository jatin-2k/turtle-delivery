package com.example.productdelivery.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAssignmentRequest {

    @Valid
    @NotNull
    @NotEmpty
    private List<LocationCoordinates> restaurantLocations;

    @Valid
    @NotNull
    @NotEmpty
    private List<LocationCoordinates> customerLocations;

    @Valid
    @NotNull
    private LocationCoordinates deliveryPersonLocation;
}
