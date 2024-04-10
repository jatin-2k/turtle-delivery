package com.example.productdelivery.service;

import com.example.productdelivery.model.DeliveryAssignmentRequest;
import com.example.productdelivery.model.LocationCoordinates;

import java.util.List;

public interface DeliveryRouteService {
    public void assignOrdersToDeliveryPerson(String deliveryPersonId, DeliveryAssignmentRequest deliveryAssignmentRequest);
    public void clearDeliveryPersonRoute(String deliveryPersonId);
    public void updateDeliveryPersonLocation(String deliveryPersonId, LocationCoordinates location);
    public List<String> getDeliveryPersonRoute(String deliveryPersonId);
    public List<String> getAllDeliveryRoutes();
    public Boolean checkDeliveryPersonIsActive(String deliveryPersonId);
}
