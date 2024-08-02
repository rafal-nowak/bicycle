package com.rafalnowak.bicycle.auth.api;


public record AuthenticationResponse(
        String token,
        AuthUserDto userDto
) {
}
