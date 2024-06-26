package com.engelandvolkers.property_listing.repositories;

import com.engelandvolkers.property_listing.entities.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    Optional<Property> getPropertyByPid(String propertyId);
}
