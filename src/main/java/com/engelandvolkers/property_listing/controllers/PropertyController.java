package com.engelandvolkers.property_listing.controllers;

import com.engelandvolkers.property_listing.dtos.PropertyDetailResponse;
import com.engelandvolkers.property_listing.dtos.PropertyDto;
import com.engelandvolkers.property_listing.entities.Property;
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

//    @GetMapping("/properties")
//    public ResponseEntity<List<PropertyDto>> getAllProperties() {
//        List<PropertyDto> properties = propertyService.getAllProperties();
//        return new ResponseEntity<>(properties, HttpStatus.OK);
//    }

    @GetMapping("/properties")
    public ResponseEntity<List<Property>> getAllProperties() {
        List<Property> properties = propertyService.getAllProperties();
        return new ResponseEntity<>(properties, HttpStatus.OK);
    }


//    @GetMapping("/{propertyId}")
//    public ResponseEntity<Object> getPropertyById(@PathVariable String propertyId) {
//        Optional<Property> property = propertyService.getPropertyByPropertyId(propertyId);
//        if (property.isPresent()) {
//            return new ResponseEntity<>(property.get(), HttpStatus.OK);
//        } else {
//            ProblemDetail pd = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, "Property with requested property id is not found");
//            return new ResponseEntity<>(pd, HttpStatus.NOT_FOUND);
//        }
//    }

    @GetMapping("/{propertyId}")
    public ResponseEntity<Object> getPropertyDetails(
            @PathVariable("propertyId") String propertyId,
            @RequestParam("userName") String userName) {

        // Get property details
        Property property = propertyService.getPropertyByPropertyId(propertyId);

        // Calculate recommended properties
        //List<Property> recommendedProperties = propertyService.getRecommendedProperties(propertyId);

        // Track property view
        //.trackPropertyView(propertyId, userName);



        //PropertyDetailResponse response = new PropertyDetailResponse(property, recommendedProperties);

        return ResponseEntity.ok(property);
    }
}
