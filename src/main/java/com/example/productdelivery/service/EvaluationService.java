package com.example.productdelivery.service;

import com.example.productdelivery.helpers.OptimalRouteHelper;
import com.example.productdelivery.model.DeliveryRoute;
import com.example.productdelivery.model.EvaluationRequest;
import com.example.productdelivery.utils.CustomObjectConverter;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;

@Service
public class EvaluationService {

    public List<String> evaluateOptimalDeliveryRoute(EvaluationRequest request) {
        // Create a new evaluation
        DeliveryRoute deliveryRoute = new DeliveryRoute(request);
        LinkedHashSet<Integer> route= OptimalRouteHelper.calculateOptimalRoute(deliveryRoute);
        return deliveryRoute.routeToString(route);
    }
}
