package com.engelandvolkers.property_listing.services;

import com.engelandvolkers.property_listing.entities.Property;
import com.engelandvolkers.property_listing.entities.PropertyView;
import com.engelandvolkers.property_listing.repositories.PropertyRepository;
import com.engelandvolkers.property_listing.repositories.PropertyViewRepository;
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
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


@Component
public class PropertyViewDataLoader {

    @Autowired
    private PropertyViewRepository propertyViewRepository;

    @Autowired
    public PropertyViewDataLoader(PropertyViewRepository propertyViewRepository) {
        this.propertyViewRepository = propertyViewRepository;
    }

    @PostConstruct
    @Transactional
    public void loadPropertyData() throws IOException {

        String csvFilePath = "property-views.csv";

        try (Reader reader = new FileReader(new ClassPathResource(csvFilePath).getFile())) {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader());

            List<PropertyView> propertyViews = new ArrayList<>();
            for (CSVRecord csvRecord : csvParser) {
                PropertyView propertyView = new PropertyView();
                propertyView.setPid(csvRecord.get("property"));
                propertyView.setUsername(csvRecord.get("user"));

                propertyViews.add(propertyView);
            }

            propertyViewRepository.saveAll(propertyViews);
        }
    }

}
