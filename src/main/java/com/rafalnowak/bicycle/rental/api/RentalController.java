package com.rafalnowak.bicycle.rental.api;

import com.rafalnowak.bicycle.rental.command.application.CreateCommand;
import com.rafalnowak.bicycle.rental.command.application.RentCommand;
import com.rafalnowak.bicycle.rental.command.application.RentalService;
import com.rafalnowak.bicycle.rental.command.application.ReturnCommand;
import com.rafalnowak.bicycle.rental.query.facade.PageUserRentalsDto;
import com.rafalnowak.bicycle.rental.query.facade.UserRentalsDto;
import com.rafalnowak.bicycle.rental.query.facade.UserRentalsFacade;
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
@RequestMapping(path = "/api/v1/rentals",
        produces = "application/json",
        consumes = "application/json"
)
class RentalController {

    private final RentalService rentalService;


    @PostMapping
    public ResponseEntity<Void> createUserRentals(@RequestBody CreateCommand createCommand){
        rentalService.create(createCommand);
        return ResponseEntity.ok().build();
    }

    @PostMapping("rent")
    public ResponseEntity<Void> rentBicycle(@RequestBody RentCommand rentCommand){
        rentalService.rentBike(rentCommand);
        return ResponseEntity.ok().build();
    }

    @PostMapping("return")
    public ResponseEntity<Void> returnBicycle(@RequestBody ReturnCommand returnCommand){
        rentalService.returnBike(returnCommand);
        return ResponseEntity.ok().build();
    }

    private final UserRentalsFacade userRentalsFacade;

    @GetMapping( path = "/{userId}")
    public ResponseEntity<UserRentalsDto> getUserRentalsForUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(userRentalsFacade.findByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<PageUserRentalsDto> getUserRentals(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);

        return ResponseEntity.ok(userRentalsFacade.findAll(pageable));
    }

}
