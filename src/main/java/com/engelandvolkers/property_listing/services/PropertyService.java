package com.engelandvolkers.property_listing.services;

import com.engelandvolkers.property_listing.dtos.PropertyDto;
import com.engelandvolkers.property_listing.entities.Property;
import com.engelandvolkers.property_listing.entities.PropertyView;
import com.engelandvolkers.property_listing.mapper.PropertyMapper;
import com.engelandvolkers.property_listing.repositories.PropertyRepository;
import com.engelandvolkers.property_listing.repositories.PropertyViewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    private PropertyViewRepository propertyViewRepository;

    private final PropertyMapper propertyMapper = PropertyMapper.INSTANCE;

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property getPropertyByPropertyId(String propertyId) {
        return propertyRepository.getPropertyByPid(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
    }

    @Transactional
    public void trackPropertyView(String propertyId, String username) {
        Property property = getPropertyByPropertyId(propertyId);

        PropertyView propertyView = new PropertyView();
        propertyView.setProperty(property);
        propertyView.setUsername(username);

        propertyViewRepository.save(propertyView);
    }


//    public List<PropertyDto> getAllProperties() {
//        List<Property> properties = propertyRepository.findAll();
//        return properties.stream()
//                .map(property -> new PropertyDto(property.getPid(), property.getName(), property.getPrice()))
//                .collect(Collectors.toList());
//    }

    public List<Property> getRecommendedProperties(String propertyId) {
        Property selectedProperty = getPropertyByPropertyId(propertyId);
        List<Property> allProperties = propertyRepository.findAll();

        List<ObjectPair<Property, Double>> propertyPairs = new ArrayList<>();

        // Compute cosine similarity and create pairs
        for (Property property : allProperties) {
            if (!property.getId().equals(selectedProperty.getId())) {
                double similarity = computeCosineSimilarity(selectedProperty, property);
                propertyPairs.add(new ObjectPair<>(property, similarity));
            }
        }

        // Sort pairs by similarity in descending order
        Collections.sort(propertyPairs, new Comparator<ObjectPair<Property, Double>>() {
            @Override
            public int compare(ObjectPair<Property, Double> pair1, ObjectPair<Property, Double> pair2) {
                return Double.compare(pair2.getSecond(), pair1.getSecond()); // Descending order
            }
        });

        // Extract sorted properties from pairs
        List<Property> recommendedProperties = new ArrayList<>();
        for (ObjectPair<Property, Double> pair : propertyPairs) {
            recommendedProperties.add(pair.getFirst());
        }

        return recommendedProperties;
    }

    // Utility class to hold property and similarity pair
    private static class ObjectPair<T, U> {
        private final T first;
        private final U second;

        public ObjectPair(T first, U second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public U getSecond() {
            return second;
        }
    }

    // Compute cosine similarity between two properties
    private double computeCosineSimilarity(Property property1, Property property2) {
        // Example: Cosine similarity calculation based on features like price, location, etc.
        // Dummy implementation, replace with actual calculation based on your requirements
        return 0.0; // Placeholder, implement your actual calculation here
    }

}

//Compute cosine similarity between the user and the property:
