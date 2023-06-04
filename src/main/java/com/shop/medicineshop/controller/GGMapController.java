package com.shop.medicineshop.controller;

import com.shop.medicineshop.service.DistanceCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/distance")
public class GGMapController {
    @Autowired
    private DistanceCalculatorService distanceCalculatorService;

    @PostMapping("/calculate")
    public double calculateDistance(@RequestBody Map<String, String> address) {
        String origin = address.get("origin");
        String destination = address.get("destination");
        System.out.println(origin + " /n" + destination);
        double distance = distanceCalculatorService.calculateDistance(origin, destination);
        System.out.println(distance);
        return distance;
    }

}
