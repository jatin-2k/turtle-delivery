package com.example.productdelivery.helpers;

import com.example.productdelivery.exception.UnexpectedSituationException;
import com.example.productdelivery.model.DeliveryRoute;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class OptimalRouteHelper {

    public static LinkedHashSet<Integer> calculateOptimalRoute(DeliveryRoute deliveryRoute) {
        PriorityQueue<Node> minH = new PriorityQueue<>(Comparator.comparingLong(a -> a.time));
        minH.add(new Node(0, new LinkedHashSet<>(Set.of(0)), 0L));
        int nodeCount = deliveryRoute.getAllLocationNodes().size();

        while(!minH.isEmpty()){
            Node cur = minH.poll();
            if(cur.visited.size() == nodeCount){
                return cur.visited;
            }
            for(int i = 0; i < nodeCount; i++){
                //if (it is a customer location && order has not been picked up yet) then ignore this location
                if(cur.index >= deliveryRoute.getCustomersStartIndex()
                    && !cur.visited.contains(
                                cur.index
                                - deliveryRoute.getCustomersStartIndex()
                                + deliveryRoute.getRestaurantsStartIndex())
                ){
                    continue;
                }
                if(!cur.visited.contains(i)){
                    LinkedHashSet<Integer> newVisited = new LinkedHashSet<>(cur.visited);
                    newVisited.add(i);
                    minH.add(new Node(i, newVisited, cur.time + deliveryRoute.getTravelTime(cur.index, i)));
                }
            }
        }

        throw new UnexpectedSituationException("Optimal route not found for deliveryRoute: {}" + deliveryRoute);
    }

    @NoArgsConstructor
    @AllArgsConstructor
    public static class Node {
        public Integer index;
        public LinkedHashSet<Integer> visited;
        public Long time;
    }
}
