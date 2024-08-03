package com.rafalnowak.bicycle.rental.command.application;

public interface AvailabilityService {
    void lockBicycle(String bicycleId, Integer userId);
    void unlockBicycle(String bicycleId, Integer userId);
}
