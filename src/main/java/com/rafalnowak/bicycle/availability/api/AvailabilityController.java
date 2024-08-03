package com.rafalnowak.bicycle.availability.api;

import com.rafalnowak.bicycle.availability.command.application.AvailabilityService;
import com.rafalnowak.bicycle.availability.command.application.CreateCommand;
import com.rafalnowak.bicycle.availability.command.application.LockCommand;
import com.rafalnowak.bicycle.availability.command.application.UnlockCommand;
import com.rafalnowak.bicycle.availability.query.facade.BicycleAvailabilityDto;
import com.rafalnowak.bicycle.availability.query.facade.BicycleAvailabilityFacade;
import com.rafalnowak.bicycle.availability.query.facade.PageBicycleAvailabilityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/availabilities",
        produces = "application/json",
        consumes = "application/json"
)
class AvailabilityController {

//    private final AvailabilityService availabilityService;
//
//    @PostMapping
//    public ResponseEntity<Void> createReservation(@RequestBody CreateCommand createCommand){
//        availabilityService.create(createCommand);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("lock")
//    public ResponseEntity<Void> lockBicycle(@RequestBody LockCommand lockCommand){
//        availabilityService.lockBicycle(lockCommand);
//        return ResponseEntity.ok().build();
//    }
//
//    @PostMapping("unlock")
//    public ResponseEntity<Void> unlockBicycle(@RequestBody UnlockCommand unlockCommand){
//        availabilityService.unlockBicycle(unlockCommand);
//        return ResponseEntity.ok().build();
//    }

    private final BicycleAvailabilityFacade availabilityFacade;

    @GetMapping( path = "/{bicycleId}")
    public ResponseEntity<BicycleAvailabilityDto> getAvailability(@PathVariable String bicycleId) {
        return ResponseEntity.ok(availabilityFacade.findByBicycleId(bicycleId));
    }

    @GetMapping
    public ResponseEntity<PageBicycleAvailabilityDto> getAvailabilities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(availabilityFacade.findAll(pageable));
    }

}
