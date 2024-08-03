package com.rafalnowak.bicycle.rental.query.facade;

import com.rafalnowak.bicycle.rental.command.domain.UserRentals;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PageUserRentalsDtoMapper {

    @Mapping(target = "rentals", qualifiedByName = "toUserRentalsDtoList")
    PageUserRentalsDto toPageDto(PageUserRentals domain);

    @Named("toUserRentalsDtoList")
    @IterableMapping(qualifiedByName = "userRentalsToUserRentalsDto")
    List<UserRentalsDto> toListDto(List<UserRentals> userRentals);

    @Named("userRentalsToUserRentalsDto")
    UserRentalsDto toDto(UserRentals domain);
}