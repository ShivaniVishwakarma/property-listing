package com.engelandvolkers.property_listing.controllers;

import com.engelandvolkers.property_listing.entities.Property;
import com.engelandvolkers.property_listing.services.PropertyRecommendationService;
import com.engelandvolkers.property_listing.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PropertyRecommendationService propertyViewService;

    @GetMapping("/properties")
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }


    @GetMapping("/{propertyId}")
    public ResponseEntity<Object> getPropertyDetails(
            @PathVariable("propertyId") String propertyId,
            @RequestParam("userName") String userName) {

        // Get property details
        Property property = propertyViewService.getPropertyByPropertyId(propertyId);

        // Calculate recommended properties
        List<String> recommendedProperties = propertyViewService.getRecommendedProperties(propertyId);
        System.out.println(recommendedProperties);

        // Track property view
        //propertyRecommendationService.trackPropertyView(propertyId, userName);

        //PropertyDetailResponse response = new PropertyDetailResponse(property, recommendedProperties);

        return ResponseEntity.ok(property);
    }
}
