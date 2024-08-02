package com.rafalnowak.bicycle.availability.query.facade;

import com.rafalnowak.bicycle.availability.command.domain.BicycleAvailability;
import com.rafalnowak.bicycle.availability.command.domain.BicycleId;
import com.rafalnowak.bicycle.availability.command.domain.UserId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BicycleAvailabilityDtoMapper extends BicycleMappingHelper {

    @Mapping(source = "bicycleId.bicycleId", target = "bicycleId")
    @Mapping(source = "userId.userId", target = "userId")
    BicycleAvailabilityDto toDto(BicycleAvailability domain);

}