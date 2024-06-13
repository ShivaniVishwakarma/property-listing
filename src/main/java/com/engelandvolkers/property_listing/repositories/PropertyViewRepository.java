package com.engelandvolkers.property_listing.repositories;

import com.engelandvolkers.property_listing.entities.Property;
import com.engelandvolkers.property_listing.entities.PropertyView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropertyViewRepository extends JpaRepository<PropertyView, Long> {

}

