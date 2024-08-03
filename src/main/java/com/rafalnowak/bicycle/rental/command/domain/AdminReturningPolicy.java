package com.rafalnowak.bicycle.rental.command.domain;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class AdminReturningPolicy implements ReturningPolicy{
    @Override
    public void returnBicycle(final UserRentals userRentals, final String bicycleId) {
        final List<String> bicycles = userRentals.getBicycles();
        bicycles.remove(bicycleId);
    }
}
