package com.engelandvolkers.property_listing.dtos;

import com.engelandvolkers.property_listing.entities.Property;

import java.util.List;

public class PropertyDetailResponse {

    private Property property;
    private List<Property> recommendedProperties;

    public PropertyDetailResponse() { }

    public PropertyDetailResponse(Property property, List<Property> recommendedProperties) {
        this.property = property;
        this.recommendedProperties = recommendedProperties;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public List<Property> getRecommendedProperties() {
        return recommendedProperties;
    }

    public void setRecommendedProperties(List<Property> recommendedProperties) {
        this.recommendedProperties = recommendedProperties;
    }
}
