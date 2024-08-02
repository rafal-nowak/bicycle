package com.rafalnowak.bicycle.availability.query.facade;

public record BicycleAvailabilityDto(
        String bicycleId,
        Integer userId,
        Integer version
) {}
