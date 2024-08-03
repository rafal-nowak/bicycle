package com.rafalnowak.bicycle.rental.command.domain;

public record User(
        Integer id,
        UserRole role
) {}