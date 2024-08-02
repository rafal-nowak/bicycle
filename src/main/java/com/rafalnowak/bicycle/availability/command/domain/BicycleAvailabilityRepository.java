package com.rafalnowak.bicycle.availability.command.domain;

import java.util.Optional;

public interface BicycleAvailabilityRepository {

    BicycleAvailability save(BicycleAvailability bicycleAvailability);

    Optional<BicycleAvailability> findBy(BicycleId bicycleId);

}
