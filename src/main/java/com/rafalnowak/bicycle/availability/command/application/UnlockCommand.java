package com.rafalnowak.bicycle.availability.command.application;

public record UnlockCommand(
        String bicycleId,
        Integer userId
) {}
