package com.example.productdelivery.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationCoordinates {
    @NotNull
    protected Double latitude;
    @NotNull
    protected Double longitude;
}
