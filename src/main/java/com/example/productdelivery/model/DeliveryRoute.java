package com.example.productdelivery.model;

import com.example.productdelivery.utils.CustomObjectConverter;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
public class DeliveryRoute {
    List<LocationNode> allLocationNodes = new java.util.ArrayList<>();
    Integer restaurantsStartIndex = 1;
    Integer customersStartIndex;
    Integer countDeliverables;
    Long[][] timeMatrix;

    public DeliveryRoute(EvaluationRequest request) {
        this.countDeliverables = request.getRestaurantLocations().size();
        this.customersStartIndex = this.restaurantsStartIndex + this.countDeliverables;

        //making it such that 0 is starting point, 1 to n indices are restaurant locations, n+1 to 2n indices are customer locations
        allLocationNodes.add(new LocationNode(request.getDeliveryPersonLocation(), LocationType.DELIVERY_PERSON));
        allLocationNodes.addAll(request.getRestaurantLocations().stream().map(restaurantLocation -> new LocationNode(restaurantLocation, LocationType.RESTAURANT)).toList());
        allLocationNodes.addAll(request.getCustomerLocations().stream().map(customerLocation -> new LocationNode(customerLocation, LocationType.CUSTOMER)).toList());

        this.timeMatrix = CustomObjectConverter.convertToTimeMatrix(allLocationNodes);
    }

    public List<String> routeToString(LinkedHashSet<Integer> route) {
        List<String> routeString = new java.util.ArrayList<>();
        for(Integer index : route){
            LocationNode cur = allLocationNodes.get(index);
            if(cur.getLocationType() == LocationType.RESTAURANT){
                routeString.add("Go to R" + (index-restaurantsStartIndex+1));
            } else if(cur.getLocationType() == LocationType.CUSTOMER){
                routeString.add("Go to C" + (index-customersStartIndex+1));
            }
        }
        return routeString;
    }

    public Long getTravelTime(Integer from, Integer to) {
        return timeMatrix[from][to];
    }
}