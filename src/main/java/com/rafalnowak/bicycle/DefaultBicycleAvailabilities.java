package com.rafalnowak.bicycle;

import com.rafalnowak.bicycle.availability.command.application.AvailabilityService;
import com.rafalnowak.bicycle.availability.command.application.CreateCommand;
import com.rafalnowak.bicycle.availability.command.domain.BicycleId;
import com.rafalnowak.bicycle.user.domain.User;
import com.rafalnowak.bicycle.user.domain.UserRole;
import com.rafalnowak.bicycle.user.domain.UserService;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Log
public class DefaultBicycleAvailabilities implements CommandLineRunner {

    private final AvailabilityService availabilityService;

    public DefaultBicycleAvailabilities(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }


    @Override
    public void run(String... args) {
        try {
            availabilityService.create(new CreateCommand("bike1"));
            availabilityService.create(new CreateCommand("bike2"));
        } catch (Exception ex) {
            log.warning(ex.getMessage());
        }
    }

}
