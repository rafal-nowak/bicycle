package com.rafalnowak.bicycle.rental.command.domain;

public interface RentingPolicy {
    void rentBicycle(UserRentals userRentals, String bicycleId);
}
