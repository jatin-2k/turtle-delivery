package com.example.productdelivery.helpers;

import com.example.productdelivery.model.LocationCoordinates;


/**
 * This is a helper class for geolocation related operations.
 */
public class GeoLocationHelper {

    private final static double AVERAGE_SPEED = 20; // Average speed in km/h

    /**
     * This method calculates the time taken to travel between two points in latitude and longitude.
     *
     * @param location1 The first location coordinates.
     * @param location2 The second location coordinates.
     * @return The time taken to travel between the two locations in seconds.
     */
    public static Long travelTimeInSeconds(LocationCoordinates location1, LocationCoordinates location2) {
        double d = distance(location1.getLatitude(), location1.getLongitude(), location2.getLatitude(), location2.getLongitude());
        double t = d / AVERAGE_SPEED; // Time in hours
        return Math.round(t * 60 * 60); // Time in seconds
    }

    /**
     * This method calculates the distance between two points in latitude and longitude using the haversine formula.
     *
     * @param lat1 The latitude of the first location.
     * @param lon1 The longitude of the first location.
     * @param lat2 The latitude of the second location.
     * @param lon2 The longitude of the second location.
     * @return The distance between the two locations in kilometers.
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2 - lat1);
        double dLon = deg2rad(lon2 - lon1);
        double a =
          Math.sin(dLat / 2) * Math.sin(dLat / 2) +
          Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
          Math.sin(dLon / 2) * Math.sin(dLon / 2)
          ;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in km
    }

    /**
     * This method converts degrees to radians.
     *
     * @param deg The value in degrees to be converted.
     * @return The value in radians.
     */
    private static double deg2rad(double deg) {
        return deg * (Math.PI / 180);
    }
}
