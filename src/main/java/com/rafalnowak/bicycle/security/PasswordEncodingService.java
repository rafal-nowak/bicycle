package com.rafalnowak.bicycle.security;

import com.rafalnowak.bicycle.user.domain.EncodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncodingService implements EncodingService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
