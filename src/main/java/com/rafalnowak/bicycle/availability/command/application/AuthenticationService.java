package com.rafalnowak.bicycle.availability.command.application;

import com.rafalnowak.bicycle.availability.command.domain.User;
import org.springframework.security.core.Authentication;


public interface AuthenticationService {
    Authentication getAuthentication();
    User getLoggedInUser();
}
