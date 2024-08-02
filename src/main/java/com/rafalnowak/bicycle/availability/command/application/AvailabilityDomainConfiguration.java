package com.rafalnowak.bicycle.availability.command.application;

import com.rafalnowak.bicycle.availability.command.domain.BicycleAvailabilityRepository;
import com.rafalnowak.bicycle.availability.command.infrastructure.storage.BicycleAvailabilityStorageAdapter;
import com.rafalnowak.bicycle.availability.command.infrastructure.storage.JpaBicycleAvailabilityRepository;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties("availability.domain.properties")
public class AvailabilityDomainConfiguration {

    @Bean
    public BicycleAvailabilityRepository bicycleAvailabilityRepository(JpaBicycleAvailabilityRepository jpaBicycleAvailabilityRepository) {
        return new BicycleAvailabilityStorageAdapter(jpaBicycleAvailabilityRepository);
    }

}
