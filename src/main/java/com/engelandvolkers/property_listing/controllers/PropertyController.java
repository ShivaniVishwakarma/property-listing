package com.engelandvolkers.property_listing.controllers;

import com.engelandvolkers.property_listing.dtos.PropertyDetailResponse;
import com.engelandvolkers.property_listing.entities.Property;
import com.engelandvolkers.property_listing.services.PropertyViewService;
import com.engelandvolkers.property_listing.services.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/task")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PropertyViewService propertyViewService;

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

        List<Property> recommendedPropertiesList = new ArrayList<>();
        for (String pid : recommendedProperties) {
            Property recommendedProperty = propertyViewService.getPropertyByPropertyId(propertyId);
            if (recommendedProperty != null) {
                recommendedPropertiesList.add(recommendedProperty);
            }
        }

        // Track property view
        propertyViewService.trackPropertyView(propertyId, userName);

        PropertyDetailResponse response = new PropertyDetailResponse(property, recommendedPropertiesList);

        return ResponseEntity.ok(response);
    }
}