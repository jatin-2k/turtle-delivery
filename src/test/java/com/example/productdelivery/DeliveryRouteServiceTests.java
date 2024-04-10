package com.example.productdelivery;

import com.example.productdelivery.model.DeliveryAssignmentRequest;
import com.example.productdelivery.model.DeliveryRoute;
import com.example.productdelivery.model.LocationCoordinates;
import com.example.productdelivery.repository.DeliveryRouteRepository;
import com.example.productdelivery.service.DeliveryRouteServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DeliveryRouteServiceTests {
    @InjectMocks
    DeliveryRouteServiceImpl deliveryRouteService;
    @Mock
    DeliveryRouteRepository deliveryRouteRepository;

    @Test
    public void testAssignOrdersToDeliveryPerson_WrongInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            deliveryRouteService.assignOrdersToDeliveryPerson("1", new DeliveryAssignmentRequest(
                    List.of(new LocationCoordinates(37.7749, -122.4194)),
                    List.of(new LocationCoordinates(37.7749, -122.4194), new LocationCoordinates(37.7749, -122.4194)),
                    new LocationCoordinates(37.7749, -122.4194)
            ));
        });
    }

    @Test
    public void testGetDeliveryPersonRoute_Empty() {
        List<String> expectedRoute = List.of();

        List<String> actualRoute = deliveryRouteService.getDeliveryPersonRoute("1");

        assertEquals(expectedRoute, actualRoute, "The calculated route did not match the expected route");
    }

    @Test
    public void testGetDeliveryPersonRoute() {
        List<String> expectedRoute = List.of(
                "Go to R1 at 34.0522,-118.2237",
                "Go to R2 at 34.0522,-118.2337",
                "Go to C1 at 34.0522,-118.2437",
                "Go to C2 at 34.0522,-118.2537"
        );

        when(deliveryRouteRepository.getRoute("1")).thenReturn(new DeliveryRoute(
                new LocationCoordinates(34.0522, -118.2137),
                List.of(new LocationCoordinates(34.0522, -118.2237), new LocationCoordinates(34.0522, -118.2337)),
                List.of(new LocationCoordinates(34.0522, -118.2437), new LocationCoordinates(34.0522, -118.2537))
        ));

        when(deliveryRouteRepository.checkDeliveryPersonExists("1")).thenReturn(true);

        List<String> actualRoute = deliveryRouteService.getDeliveryPersonRoute("1");

        assertEquals(expectedRoute, actualRoute, "The calculated route did not match the expected route");
    }

    @Test
    public void testGetAllDeliveryRoutes() {
        List<String> expectedRoutes = List.of("1",
                "Go to R1 at 34.0522,-118.2237",
                "Go to R2 at 34.0522,-118.2337",
                "Go to C1 at 34.0522,-118.2437",
                "Go to C2 at 34.0522,-118.2537"
        );

        when(deliveryRouteRepository.getAllDeliveryRoutes()).thenReturn(Map.of(
                "1", new DeliveryRoute(
                        new LocationCoordinates(34.0522, -118.2137),
                        List.of(new LocationCoordinates(34.0522, -118.2237), new LocationCoordinates(34.0522, -118.2337)),
                        List.of(new LocationCoordinates(34.0522, -118.2437), new LocationCoordinates(34.0522, -118.2537))
                )
        ));

        List<String> actualRoutes = deliveryRouteService.getAllDeliveryRoutes();

        assertEquals(expectedRoutes, actualRoutes, "The calculated routes did not match the expected routes");
    }

    @Test
    public void testCheckDeliveryPersonIsActive() {
        when(deliveryRouteRepository.checkDeliveryPersonExists("1")).thenReturn(true);

        Boolean isActive = deliveryRouteService.checkDeliveryPersonIsActive("1");

        assertEquals(true, isActive, "The delivery person is not active");
    }

}
