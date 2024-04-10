package com.example.productdelivery.controller;

import com.example.productdelivery.model.EvaluationRequest;
import com.example.productdelivery.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/evaluate")
public class EvaluationController {

    private final EvaluationService evaluationService;

    @PostMapping("/")
    public ResponseEntity<List<String>> evaluateOptimalDeliveryRoute(@RequestBody EvaluationRequest request) {
        if(request.getCustomerLocations().size() != request.getRestaurantLocations().size())
            return ResponseEntity.badRequest().body(List.of("Number of customer locations should be equal to number of restaurant locations"));

        // Create a new evaluation
        return ResponseEntity.ok(evaluationService.evaluateOptimalDeliveryRoute(request));
    }
}
