package com.rafalnowak.bicycle.user.api;

import com.rafalnowak.bicycle.user.domain.PageUser;
import com.rafalnowak.bicycle.user.domain.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PageUserDtoMapper {

    @Mapping(target = "users", qualifiedByName = "toUserDtoList")
    PageUserDto toPageDto(PageUser domain);

    @Named("toUserDtoList")
    @IterableMapping(qualifiedByName = "userToUserDto")
    List<UserDto> toListDto(List<User> users);

    @Named("userToUserDto")
    @Mapping(target="password", constant = "######")
    UserDto toDto(User domain);
}