package com.rafalnowak.bicycle.rental.command.infrastructure.availability;

import com.rafalnowak.bicycle.availability.command.application.LockCommand;
import com.rafalnowak.bicycle.availability.command.application.UnlockCommand;
import com.rafalnowak.bicycle.rental.command.application.AvailabilityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AvailabilityFacade implements AvailabilityService {
    public final com.rafalnowak.bicycle.availability.command.application.AvailabilityService availabilityService;

    @Override
    public void lockBicycle(final String bicycleId, Integer userId) {
        availabilityService.lockBicycle(new LockCommand(bicycleId, userId));
    }

    @Override
    public void unlockBicycle(final String bicycleId, Integer userId) {
        availabilityService.unlockBicycle(new UnlockCommand(bicycleId, userId));
    }
}
