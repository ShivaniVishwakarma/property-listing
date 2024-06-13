package com.engelandvolkers.property_listing.services;

import com.engelandvolkers.property_listing.dtos.PropertyDto;
import com.engelandvolkers.property_listing.entities.Property;
import com.engelandvolkers.property_listing.mapper.PropertyMapper;
import com.engelandvolkers.property_listing.repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    private final PropertyMapper propertyMapper = PropertyMapper.INSTANCE;

    public Optional<PropertyDto> getPropertyById(Long id){
    return propertyRepository.findById(id).map(propertyMapper::toDto);
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

//    public List<PropertyDto> getAllProperties() {
//        List<Property> properties = propertyRepository.findAll();
//        return properties.stream()
//                .map(property -> new PropertyDto(property.getPid(), property.getName(), property.getPrice()))
//                .collect(Collectors.toList());
//    }

}
