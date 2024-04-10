package com.example.productdelivery;

import com.example.productdelivery.helpers.OptimalRouteHelper;
import com.example.productdelivery.model.DeliveryRoute;
import com.example.productdelivery.model.LocationCoordinates;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OptimalRouteHelperTests {

    @Test
    public void testOptimalRoute1(){

        DeliveryRoute deliveryRoute = new DeliveryRoute(
                new LocationCoordinates(34.0522, -118.2137),
                List.of(new LocationCoordinates(34.0522, -118.2237), new LocationCoordinates(34.0522, -118.2337)),
                List.of(new LocationCoordinates(34.0522, -118.2437), new LocationCoordinates(34.0522, -118.2537))
        );

        List<Integer> expectedRoute = List.of(0,1,2,3,4);

        List<Integer> actualRoute = OptimalRouteHelper.calculateOptimalRoute(deliveryRoute).stream().toList();

        assertEquals(expectedRoute, actualRoute, "The calculated route did not match the expected route");
    }

    @Test
    public void testOptimalRoute2(){

        DeliveryRoute deliveryRoute = new DeliveryRoute(
                new LocationCoordinates(34.0522, -118.2137),
                List.of(new LocationCoordinates(34.0522, -118.2237), new LocationCoordinates(34.0522, -118.2437)),
                List.of(new LocationCoordinates(34.0522, -118.2337), new LocationCoordinates(34.0522, -118.2537))
        );

        List<Integer> expectedRoute = List.of(0,1,3,2,4);

        List<Integer> actualRoute = OptimalRouteHelper.calculateOptimalRoute(deliveryRoute).stream().toList();

        assertEquals(expectedRoute, actualRoute, "The calculated route did not match the expected route");
    }
}
