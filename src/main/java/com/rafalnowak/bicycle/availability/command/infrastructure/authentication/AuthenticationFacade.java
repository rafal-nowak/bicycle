package com.rafalnowak.bicycle.availability.command.infrastructure.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.rafalnowak.bicycle.availability.command.application.AuthenticationService;
import com.rafalnowak.bicycle.security.UserDetailsImpl;
import com.rafalnowak.bicycle.user.domain.UserService;
import com.rafalnowak.bicycle.user.domain.User;

@Component
@RequiredArgsConstructor
public class AuthenticationFacade implements AuthenticationService {
    private final UserService userService;
    private final UserAuthenticationMapper mapper;
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public com.rafalnowak.bicycle.availability.command.domain.User getLoggedInUser() {
        Authentication authentication = getAuthentication();
        User user = userService.findByEmail(((UserDetailsImpl) authentication.getPrincipal()).getUsername());
        return mapper.toAvailabilityContext(user);
    }

}