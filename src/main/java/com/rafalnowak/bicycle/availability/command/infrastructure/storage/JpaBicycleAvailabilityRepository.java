package com.rafalnowak.bicycle.availability.command.infrastructure.storage;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import com.rafalnowak.bicycle.availability.command.domain.BicycleAvailability;
import com.rafalnowak.bicycle.availability.command.domain.BicycleId;

import java.util.Optional;

public interface JpaBicycleAvailabilityRepository extends JpaRepository<BicycleAvailability, Integer> {
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Optional<BicycleAvailability> findByBicycleId(BicycleId bicycleId);
}
