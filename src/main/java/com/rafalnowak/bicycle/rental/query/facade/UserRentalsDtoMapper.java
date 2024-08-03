package com.rafalnowak.bicycle.rental.query.facade;

import com.rafalnowak.bicycle.rental.command.domain.UserRentals;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRentalsDtoMapper {

    UserRentalsDto toDto(UserRentals domain);

}