package com.rafalnowak.bicycle.availability.query.facade;

import com.rafalnowak.bicycle.availability.command.domain.BicycleAvailability;
import com.rafalnowak.bicycle.availability.command.domain.BicycleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaQueryBicycleAvailabilityRepository extends JpaRepository<BicycleAvailability, Integer> {
    Optional<BicycleAvailability> findByBicycleId(BicycleId bicycleId);
}
