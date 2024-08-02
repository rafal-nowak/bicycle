package com.rafalnowak.bicycle.availability.query.facade;

import com.rafalnowak.bicycle.availability.command.domain.BicycleId;
import com.rafalnowak.bicycle.availability.command.domain.UserId;

public interface BicycleMappingHelper {

    default String map(BicycleId value) {
        return value != null ? value.asString() : null;
    }

    default Integer map(UserId value) {
        return value != null ? value.userId() : null;
    }
}
