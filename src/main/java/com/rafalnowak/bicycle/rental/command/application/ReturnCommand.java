package com.rafalnowak.bicycle.rental.command.application;

public record ReturnCommand(
        String bicycleId,
        Integer userId
) {}
