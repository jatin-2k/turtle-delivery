package com.example.productdelivery.service;

import com.example.productdelivery.model.DeliveryAssignmentRequest;
import com.example.productdelivery.model.DeliveryRoute;
import com.example.productdelivery.model.LocationCoordinates;
import com.example.productdelivery.repository.DeliveryRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryRouteService {
    private final DeliveryRouteRepository deliveryRouteRepository;

    public void assignOrdersToDeliveryPerson(String deliveryPersonId, DeliveryAssignmentRequest deliveryAssignmentRequest) {
        deliveryRouteRepository.saveRoute(deliveryPersonId, new DeliveryRoute(deliveryAssignmentRequest));
    }

    public void clearDeliveryPersonRoute(String deliveryPersonId) {
        deliveryRouteRepository.removeRoute(deliveryPersonId);
    }

    public void updateDeliveryPersonLocation(String deliveryPersonId, LocationCoordinates location) {
        DeliveryRoute route = deliveryRouteRepository.getRoute(deliveryPersonId);
        route.setDeliveryPersonLocation(location);

        deliveryRouteRepository.saveRoute(deliveryPersonId, route);
    }

    public List<String> getDeliveryPersonRoute(String deliveryPersonId) {
        if(!checkDeliveryPersonExists(deliveryPersonId)) {
            return List.of();
        }
        DeliveryRoute route = deliveryRouteRepository.getRoute(deliveryPersonId);
        return route.getMostOptimalRoute();
    }

    public List<String> getAllDeliveryRoutes() {
        List<String> allRoutes = new java.util.ArrayList<>();
        deliveryRouteRepository.getAllDeliveryRoutes().forEach((key, value) -> {
            allRoutes.add(key);
            allRoutes.addAll(value.getMostOptimalRoute());
        });
        return allRoutes;
    }

    public Boolean checkDeliveryPersonExists(String deliveryPersonId) {
        return deliveryRouteRepository.checkDeliveryPersonExists(deliveryPersonId);
    }
}
