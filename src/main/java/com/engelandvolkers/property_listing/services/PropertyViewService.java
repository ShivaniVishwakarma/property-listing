package com.engelandvolkers.property_listing.services;

import com.engelandvolkers.property_listing.entities.Property;
import com.engelandvolkers.property_listing.entities.PropertyView;
import com.engelandvolkers.property_listing.repositories.PropertyRepository;
import com.engelandvolkers.property_listing.repositories.PropertyViewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PropertyViewService {

    @Autowired
    private PropertyViewRepository propertyViewRepository;

    @Autowired
    private PropertyRepository propertyRepository;

    // Mocked data
    private Map<String, Map<String, Integer>> userPropertyViews;

    public PropertyViewService() {
        userPropertyViews = new HashMap<>();

        Map<String, Integer> user1Views = new HashMap<>();
        user1Views.put("Property1", 1);
        user1Views.put("Property2", 1);
        user1Views.put("Property3", 0);
        userPropertyViews.put("user1", user1Views);

        Map<String, Integer> user2Views = new HashMap<>();
        user2Views.put("Property1", 0);
        user2Views.put("Property2", 1);
        user2Views.put("Property3", 0);
        userPropertyViews.put("user2", user2Views);

        Map<String, Integer> user3Views = new HashMap<>();
        user3Views.put("Property1", 1);
        user3Views.put("Property2", 1);
        user3Views.put("Property3", 1);
        userPropertyViews.put("user3", user3Views);
    }

    public Property getPropertyByPropertyId(String propertyId) {
        return propertyRepository.getPropertyByPid(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
    }

    public List<String> getRecommendedProperties(String selectedPropertyPid) {
        Map<String, Integer> selectedPropertyViews = getPropertyViewsForProperty(selectedPropertyPid);

        Map<String, Double> propertySimilarities = new HashMap<>();

        for (String propertyPid : getAllPropertyPids()) {
            if (!propertyPid.equals(selectedPropertyPid)) {
                Map<String, Integer> otherPropertyViews = getPropertyViewsForProperty(propertyPid);
                double similarity = cosineSimilarity(selectedPropertyViews, otherPropertyViews);
                propertySimilarities.put(propertyPid, similarity);
            }
        }

        // Sort properties
        List<String> recommendedProperties = new ArrayList<>(propertySimilarities.keySet());
        recommendedProperties.sort((p1, p2) -> Double.compare(propertySimilarities.get(p2), propertySimilarities.get(p1)));

        return recommendedProperties;
    }

    private Map<String, Integer> getPropertyViewsForProperty(String propertyPid) {
        Map<String, Integer> propertyViews = new HashMap<>();

        for (Map.Entry<String, Map<String, Integer>> userEntry : userPropertyViews.entrySet()) {
            String userId = userEntry.getKey();
            Map<String, Integer> views = userEntry.getValue();

            if (views.containsKey(propertyPid)) {
                propertyViews.put(userId, views.get(propertyPid));
            }
        }

        return propertyViews;
    }

    private Set<String> getAllPropertyPids() {
        Set<String> allPropertyPids = new HashSet<>();

        for (Map<String, Integer> userViews : userPropertyViews.values()) {
            allPropertyPids.addAll(userViews.keySet());
        }

        return allPropertyPids;
    }

    private double cosineSimilarity(Map<String, Integer> vector1, Map<String, Integer> vector2) {
        double dotProduct = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        // Calculate dot product and norms
        for (String key : vector1.keySet()) {
            int value1 = vector1.getOrDefault(key, 0);
            int value2 = vector2.getOrDefault(key, 0);
            dotProduct += value1 * value2;
            norm1 += Math.pow(value1, 2);
            norm2 += Math.pow(value2, 2);
        }

        // Avoid division by zero
        if (norm1 == 0 || norm2 == 0) {
            return 0.0;
        }

        // Calculate cosine similarity
        return dotProduct / (Math.sqrt(norm1) * Math.sqrt(norm2));
    }

    @Transactional
    public void trackPropertyView(String propertyId, String username) {
        Property property = getPropertyByPropertyId(propertyId);

        PropertyView propertyView = new PropertyView();
        propertyView.setProperty(property);
        propertyView.setUsername(username);

        propertyViewRepository.save(propertyView);
    }

//    public static void main(String[] args) {
//        PropertyViewService service = new PropertyViewService();
//
//        String selectedProperty = "Property2";
//        List<String> recommendations = service.getRecommendedProperties(selectedProperty);
//
//        System.out.println("Recommended properties for " + selectedProperty + ":");
//        for (String property : recommendations) {
//            System.out.println(property);
//        }
//    }
}
