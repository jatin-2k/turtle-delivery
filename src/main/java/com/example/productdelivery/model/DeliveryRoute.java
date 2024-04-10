package com.example.productdelivery.model;

import com.example.productdelivery.helpers.OptimalRouteHelper;
import com.example.productdelivery.utils.CustomObjectConverter;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.List;

@Data
public class DeliveryRoute {
    List<LocationNode> allLocationNodes = new java.util.ArrayList<>();
    Integer restaurantsStartIndex = 1;
    Integer customersStartIndex;
    Integer countDeliverables;
    Long[][] timeMatrix;

    public DeliveryRoute(LocationCoordinates deliveryPersonLocation, List<LocationCoordinates> restauntLocations, List<LocationCoordinates> customerLocations) {
        this.countDeliverables = restauntLocations.size();
        this.customersStartIndex = this.restaurantsStartIndex + this.countDeliverables;

        //making it such that 0 is starting point, 1 to n indices are restaurant locations, n+1 to 2n indices are customer locations
        allLocationNodes.add(new LocationNode(deliveryPersonLocation, LocationType.DELIVERY_PERSON));
        allLocationNodes.addAll(restauntLocations.stream().map(restaurantLocation -> new LocationNode(restaurantLocation, LocationType.RESTAURANT)).toList());
        allLocationNodes.addAll(customerLocations.stream().map(customerLocation -> new LocationNode(customerLocation, LocationType.CUSTOMER)).toList());

        this.timeMatrix = CustomObjectConverter.convertToTimeMatrix(allLocationNodes);
    }

    private List<String> routeToString(LinkedHashSet<Integer> route) {
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

    public void setDeliveryPersonLocation(LocationCoordinates deliveryPersonLocation) {
        allLocationNodes.set(0, new LocationNode(deliveryPersonLocation, LocationType.DELIVERY_PERSON));
        this.timeMatrix = CustomObjectConverter.convertToTimeMatrix(allLocationNodes);
    }

    public List<String> getMostOptimalRoute() {
        LinkedHashSet<Integer> optimalRoute = OptimalRouteHelper.calculateOptimalRoute(this);
        return routeToString(optimalRoute);
    }
}
