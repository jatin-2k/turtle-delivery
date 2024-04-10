package com.example.productdelivery.controller;

import com.example.productdelivery.model.DeliveryAssignmentRequest;
import com.example.productdelivery.model.LocationCoordinates;
import com.example.productdelivery.service.DeliveryRouteService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/deliveries")
public class DeliveryRoutingController {

    private final DeliveryRouteService deliveryRouteService;

    @Operation(summary = "Assign orders to a delivery person. This will override any previous orders assigned to the delivery person.")
    @PostMapping("/{deliveryPersonId}")
    public ResponseEntity<String> assignOrdersToDeliveryPerson(@RequestParam String deliveryPersonId, @Valid @RequestBody DeliveryAssignmentRequest deliveryAssignmentRequest) {
        if(deliveryAssignmentRequest.getCustomerLocations().size() != deliveryAssignmentRequest.getRestaurantLocations().size()) {
            return ResponseEntity.badRequest().body("Number of restaurant locations and customer locations should be equal");
        }
        deliveryRouteService.assignOrdersToDeliveryPerson(deliveryPersonId, deliveryAssignmentRequest);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Clear all assigned orders and the delivery route of a delivery person.")
    @DeleteMapping("/{deliveryPersonId}")
    public ResponseEntity<String> clearDeliveryPersonRoute(@RequestParam String deliveryPersonId) {
        deliveryRouteService.clearDeliveryPersonRoute(deliveryPersonId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update the location of a delivery person.")
    @PutMapping("/{deliveryPersonId}/location")
    public ResponseEntity<String> updateDeliveryPersonLocation(@RequestParam String deliveryPersonId, @Valid @RequestBody LocationCoordinates location) {
        if(!deliveryRouteService.checkDeliveryPersonIsActive(deliveryPersonId)) {
            return ResponseEntity.badRequest().body("Delivery person does not exist");
        }
        deliveryRouteService.updateDeliveryPersonLocation(deliveryPersonId, location);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get the delivery route of a delivery person.")
    @GetMapping("/{deliveryPersonId}/route")
    public ResponseEntity<List<String>> getDeliveryPersonRoute(@RequestParam String deliveryPersonId) {
        return ResponseEntity.ok(deliveryRouteService.getDeliveryPersonRoute(deliveryPersonId));
    }

    @Operation(summary = "Get all active delivery routes.")
    @GetMapping("/")
    public ResponseEntity<List<String>> getAllActiveRoutes() {
        return ResponseEntity.ok(deliveryRouteService.getAllDeliveryRoutes());
    }
}
