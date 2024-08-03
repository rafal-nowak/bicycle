package com.rafalnowak.bicycle.rental.command.domain;

public interface ReturningPolicy {
    void returnBicycle(UserRentals userRentals, String bicycleId);
}
