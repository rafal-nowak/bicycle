package com.rafalnowak.bicycle.rental.command.application;

import com.rafalnowak.bicycle.rental.command.domain.User;
import org.springframework.security.core.Authentication;

public interface AuthenticationService {
    Authentication getAuthentication();
    User getLoggedInUser();
}
