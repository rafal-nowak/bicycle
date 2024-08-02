package com.rafalnowak.bicycle.availability.command.application;

public record LockCommand(
        String bicycleId,
        Integer userId
) {}
