package com.rafalnowak.bicycle.availability.command.domain;

public record BicycleId(String bicycleId) {

    public static BicycleId of(String bicycleId) {
        return new BicycleId(bicycleId);
    }

    public String asString() {
        return bicycleId;
    }
}
