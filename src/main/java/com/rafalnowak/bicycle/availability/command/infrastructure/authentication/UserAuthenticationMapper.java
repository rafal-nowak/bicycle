package com.rafalnowak.bicycle.availability.command.infrastructure.authentication;


import org.mapstruct.Mapper;
import com.rafalnowak.bicycle.availability.command.domain.User;


@Mapper(componentModel = "spring")
public interface UserAuthenticationMapper {

    User toAvailabilityContext(com.rafalnowak.bicycle.user.domain.User user);

}
