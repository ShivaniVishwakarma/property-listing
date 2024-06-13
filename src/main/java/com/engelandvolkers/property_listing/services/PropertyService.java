package com.engelandvolkers.property_listing.services;

import com.engelandvolkers.property_listing.dtos.PropertyDto;
import com.engelandvolkers.property_listing.entities.Property;
import com.engelandvolkers.property_listing.entities.PropertyView;
import com.engelandvolkers.property_listing.repositories.PropertyRepository;
import com.engelandvolkers.property_listing.repositories.PropertyViewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }


//    public List<PropertyDto> getAllProperties() {
//        List<Property> properties = propertyRepository.findAll();
//        return properties.stream()
//                .map(property -> new PropertyDto(property.getPid(), property.getName(), property.getPrice()))
//                .collect(Collectors.toList());
//    }
//
}
