package com.rafalnowak.bicycle.auth.api;

public record AuthenticationRequest(
        String username,
        String password
) {
}
