package com.rafalnowak.bicycle.rental.command.application;

public interface AvailabilityService {
    void lockBicycle(String bicycleId);
    void unlockBicycle(String bicycleId);
}
