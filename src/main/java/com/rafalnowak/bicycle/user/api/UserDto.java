package com.rafalnowak.bicycle.user.api;

public record UserDto(
        Integer id,
        String email,
        String name,
        String password,
        String role
) {}
