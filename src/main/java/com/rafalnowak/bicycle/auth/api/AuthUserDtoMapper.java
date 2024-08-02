package com.rafalnowak.bicycle.auth.api;

import com.rafalnowak.bicycle.user.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthUserDtoMapper {

    @Mapping(target="password", constant = "######")
    AuthUserDto toDto(User domain);

}