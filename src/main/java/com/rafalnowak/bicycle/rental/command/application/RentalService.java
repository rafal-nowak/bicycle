package com.rafalnowak.bicycle.rental.command.application;

import com.rafalnowak.bicycle.availability.command.application.AvailabilityService;
import com.rafalnowak.bicycle.availability.command.application.CreateCommand;

import com.rafalnowak.bicycle.availability.command.application.UnlockCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RentalService {


//    private final AuthenticationService authenticationService;
    private final AvailabilityService availabilityService;

    public void test() {
        availabilityService.unlockBicycle(new UnlockCommand("bike1", 1));
    }

//    public Reservation create(final CreateCommand createCommand) {
//        return reservationRepository.save(ReservationFactory.createReservation(createCommand.reservationNumber(), createCommand.amountOfSeats()));
//    }
//
//    public void removeByReservationNumber(String reservationNumber) {
//        Reservation reservation = findByReservationNumber(reservationNumber);        reservationRepository.remove(reservation.getId());
//    }
//
//    public Reservation findByReservationNumber(String reservationNumber) {
//
//        final Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber)
//                .orElseThrow(ReservationNotFoundException::new);
//        return reservation;
//    }
//
//    public void bookSeats(String reservationNumber, BookCommand bookCommand) {
//        User user = authenticationService.getLoggedInUser();
//        Reservation reservation = ReservationFactory.prepareReservationForUser(findByReservationNumber(reservationNumber), user);
//        if (bookCommand.userId() == null) {
//            reservation.bookSeats(user.id(), bookCommand.seatNumbers());
//        } else {
//            if (user.role() != UserRole.ADMIN) {
//                throw new MethodNotAllowedException();
//            }
//            reservation.bookSeats(bookCommand.userId(), bookCommand.seatNumbers());
//        }
//
//
//    }
//
//    public void releaseSeats(String reservationNumber, final ReleaseCommand releaseCommand) {
//        User user = authenticationService.getLoggedInUser();
//        Reservation reservation = ReservationFactory.prepareReservationForUser(findByReservationNumber(reservationNumber), user);
//        reservation.releaseSeats(user.id(), releaseCommand.seatNumbers());
//    }

}