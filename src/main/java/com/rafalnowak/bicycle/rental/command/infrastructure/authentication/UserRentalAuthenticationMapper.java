package com.rafalnowak.bicycle.rental.command.infrastructure.authentication;

import com.rafalnowak.bicycle.rental.command.domain.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserRentalAuthenticationMapper {

    User toReservationContext(com.rafalnowak.bicycle.user.domain.User user);

}
