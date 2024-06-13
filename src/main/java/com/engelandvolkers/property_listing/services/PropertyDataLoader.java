package com.engelandvolkers.property_listing.services;

import com.engelandvolkers.property_listing.entities.Property;
import com.engelandvolkers.property_listing.repositories.PropertyRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


@Component
public class PropertyDataLoader {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    public PropertyDataLoader(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @PostConstruct
    @Transactional
    public void loadPropertyData() throws IOException {

        String csvFilePath = "properties.csv";

        try (Reader reader = new FileReader(new ClassPathResource(csvFilePath).getFile())) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader());

            List<Property> properties = new ArrayList<>();
            for (CSVRecord csvRecord : csvParser) {
                Property property = new Property();
                property.setPid(csvRecord.get("id"));
                property.setName(csvRecord.get("name"));
                property.setDetails(csvRecord.get("details"));
                property.setPrice(Double.parseDouble(csvRecord.get("price")));

                properties.add(property);
            }

            propertyRepository.saveAll(properties);
        }
    }

}
