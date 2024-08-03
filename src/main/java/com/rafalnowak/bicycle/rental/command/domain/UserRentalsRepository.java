package com.rafalnowak.bicycle.rental.command.domain;

import java.util.Optional;

public interface UserRentalsRepository {

    UserRentals save(UserRentals userRentals);

    Optional<UserRentals> findBy(Integer userId);

}