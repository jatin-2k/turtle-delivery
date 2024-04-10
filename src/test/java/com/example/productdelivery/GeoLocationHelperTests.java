package com.example.productdelivery;

import com.example.productdelivery.helpers.GeoLocationHelper;
import com.example.productdelivery.model.LocationCoordinates;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GeoLocationHelperTests {
    @Test
    public void testCalculateDistance() {

        double lat1 = 37.7749;
        double lon1 = -122.4194;
        double lat2 = 34.0522;
        double lon2 = -118.2437;

        double expectedDistance = 559.1205770615533;

        double actualDistance = GeoLocationHelper.distance(lat1, lon1, lat2, lon2);

        assertEquals(expectedDistance, actualDistance, "The calculated distance did not match the expected distance");
    }

    @Test
    public void testTravelTime() {
        LocationCoordinates location1 = new LocationCoordinates(37.7749, -122.4194);
        LocationCoordinates location2 = new LocationCoordinates(34.0522, -118.2437);

        Long expectedTravelTime = 100642L;

        Long actualTravelTime = GeoLocationHelper.travelTimeInSeconds(location1, location2);

        assertEquals(expectedTravelTime, actualTravelTime, "The calculated travel time did not match the expected travel time");
    }
}