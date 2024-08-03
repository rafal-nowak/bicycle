package com.rafalnowak.bicycle.rental.command.application;

import com.rafalnowak.bicycle.rental.command.domain.UserRentalsRepository;
import com.rafalnowak.bicycle.rental.command.infrastructure.storage.JpaUserRentalsRepository;
import com.rafalnowak.bicycle.rental.command.infrastructure.storage.UserRentalsStorageAdapter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("reservation.domain.properties")
public class RentalDomainConfiguration {

    @Bean
    public UserRentalsRepository userRentalsRepository(JpaUserRentalsRepository jpaUserRentalsRepository) {
        return new UserRentalsStorageAdapter(jpaUserRentalsRepository);
    }

}
