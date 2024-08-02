package com.rafalnowak.bicycle.availability.command.infrastructure.storage;


import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.dao.DataIntegrityViolationException;
import com.rafalnowak.bicycle.availability.command.domain.BicycleAlreadyExistsException;
import com.rafalnowak.bicycle.availability.command.domain.BicycleAvailability;
import com.rafalnowak.bicycle.availability.command.domain.BicycleAvailabilityRepository;
import com.rafalnowak.bicycle.availability.command.domain.BicycleId;

import java.util.Optional;

@RequiredArgsConstructor
@Log
public
class BicycleAvailabilityStorageAdapter implements BicycleAvailabilityRepository {

    private final JpaBicycleAvailabilityRepository bicycleAvailabilityRepository;

    @Override
    public BicycleAvailability save(final BicycleAvailability bicycleAvailability) {
        try {
            BicycleAvailability saved = bicycleAvailabilityRepository.save(bicycleAvailability);
            log.info("Saved entity " + saved);
            return saved;
        } catch (DataIntegrityViolationException ex) {
            log.warning("Bicycle Availability with number " + bicycleAvailability.getBicycleId().asString() + " already exits in db");
            throw new BicycleAlreadyExistsException();
        }
    }

    @Override
    public Optional<BicycleAvailability> findBy(final BicycleId bicycleId) {
        return bicycleAvailabilityRepository.findByBicycleId(bicycleId);
    }

}
