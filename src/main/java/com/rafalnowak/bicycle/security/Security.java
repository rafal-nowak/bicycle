package com.rafalnowak.bicycle.security;


import com.rafalnowak.bicycle.user.domain.User;
import com.rafalnowak.bicycle.user.domain.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class Security {

    private final UserService userService;

    public User getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByEmail(((UserDetailsImpl) authentication.getPrincipal()).getUsername());
    }
}
