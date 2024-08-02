package com.rafalnowak.bicycle.availability.command.domain;

public record UserId(Integer userId) {

    public static UserId of(Integer userId
    ) {
        return new UserId(userId);
    }

    public String asString() {
        return userId.toString();
    }
}
