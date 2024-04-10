package com.example.productdelivery.repository;

import com.example.productdelivery.model.DeliveryRoute;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DeliveryRouteRepository {

    private final Map<String, DeliveryRoute> activeRoutes = new HashMap<>();

    public void saveRoute(String deliveryExecutiveId, DeliveryRoute route) {
        activeRoutes.put(deliveryExecutiveId, route);
    }

    public DeliveryRoute getRoute(String deliveryExecutiveId) {
        return activeRoutes.get(deliveryExecutiveId);
    }

    public void removeRoute(String deliveryExecutiveId) {
        activeRoutes.remove(deliveryExecutiveId);
    }

    public Map<String, DeliveryRoute> getAllDeliveryRoutes() {
        return Map.copyOf(activeRoutes);
    }

    public Boolean checkDeliveryPersonExists(String deliveryPersonId) {
        return activeRoutes.containsKey(deliveryPersonId);
    }
}
