package com.rafalnowak.bicycle.rental.command.application;

public record RentCommand(
        String bicycleId,
        Integer userId
) {}
