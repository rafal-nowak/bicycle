package com.rafalnowak.bicycle.availability.command.domain;

public record User(
        Integer id,
        UserRole role
) {}