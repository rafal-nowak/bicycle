package com.rafalnowak.bicycle.rental.command.infrastructure.authentication;


import com.rafalnowak.bicycle.rental.command.application.AuthenticationService;
import com.rafalnowak.bicycle.security.UserDetailsImpl;
import com.rafalnowak.bicycle.user.domain.User;
import com.rafalnowak.bicycle.user.domain.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalAuthenticationFacade implements AuthenticationService {
    private final UserService userService;
    private final UserRentalAuthenticationMapper mapper;
    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public com.rafalnowak.bicycle.rental.command.domain.User getLoggedInUser() {
        Authentication authentication = getAuthentication();
        User user = userService.findByEmail(((UserDetailsImpl) authentication.getPrincipal()).getUsername());
        return mapper.toReservationContext(user);
    }

}