package com.example.productdelivery.utils;

import com.example.productdelivery.helpers.GeoLocationHelper;
import com.example.productdelivery.model.LocationCoordinates;
import com.example.productdelivery.model.LocationNode;

import java.util.List;

public class CustomObjectConverter {

    //convert evaluation request to time matrix for each location
    public static Long[][] convertToTimeMatrix(List<LocationNode> arr) {
        //convert evaluation request to time matrix for each location
        int size = arr.size();

        Long[][] timeMatrix = new Long[size][size];
        //initialize time matrix with 0
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                timeMatrix[i][j] = 0L;
            }
        }

        //calculate time matrix for each location
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                LocationCoordinates from = arr.get(i);
                LocationCoordinates to = arr.get(j);
                timeMatrix[i][j] = calculateTime(from, to);
            }
        }

        return timeMatrix;
    }

    private static Long calculateTime(LocationCoordinates from, LocationCoordinates to){
        if(from == to) return 0L; //same location
        //calculate time between two locations
        return GeoLocationHelper.travelTimeInSeconds(from, to);
    }
}
