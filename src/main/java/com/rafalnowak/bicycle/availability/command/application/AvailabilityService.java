package com.rafalnowak.bicycle.availability.command.application;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rafalnowak.bicycle.availability.command.domain.BicycleAvailability;
import com.rafalnowak.bicycle.availability.command.domain.BicycleAvailabilityRepository;
import com.rafalnowak.bicycle.availability.command.domain.BicycleId;
import com.rafalnowak.bicycle.availability.command.domain.BicycleNotFoundException;
import com.rafalnowak.bicycle.availability.command.domain.MethodNotAllowedException;
import com.rafalnowak.bicycle.availability.command.domain.User;
import com.rafalnowak.bicycle.availability.command.domain.UserId;
import com.rafalnowak.bicycle.availability.command.domain.UserRole;

@Service
@Transactional
@RequiredArgsConstructor
public class AvailabilityService {

    private final BicycleAvailabilityRepository bicycleAvailabilityRepository;
    private final AuthenticationService authenticationService;

    public BicycleAvailability create(final CreateCommand createCommand) {
        return bicycleAvailabilityRepository.save(new BicycleAvailability(BicycleId.of(createCommand.bicycleId())));
    }

    public BicycleAvailability findByBicycleId(BicycleId bicycleId) {

        final BicycleAvailability bicycleAvailability = bicycleAvailabilityRepository.findBy(bicycleId)
                .orElseThrow(BicycleNotFoundException::new);
        return bicycleAvailability;
    }

    public void lockBicycle(LockCommand lockCommand) {
        System.out.println("####### lock bicycle");
        User user = authenticationService.getLoggedInUser();
        BicycleAvailability bicycleAvailability = findByBicycleId(BicycleId.of(lockCommand.bicycleId()));
        if (lockCommand.userId() == null) {
            bicycleAvailability.lockFor(UserId.of(user.id()));
        } else {
            if (user.role() != UserRole.ADMIN) {
                throw new MethodNotAllowedException();
            }
            bicycleAvailability.lockFor(UserId.of(lockCommand.userId()));
        }


    }

    public void unlockBicycle(UnlockCommand unlockCommand) {
        User user = authenticationService.getLoggedInUser();
        BicycleAvailability bicycleAvailability = findByBicycleId(BicycleId.of(unlockCommand.bicycleId()));
        bicycleAvailability.unlock();
    }

}