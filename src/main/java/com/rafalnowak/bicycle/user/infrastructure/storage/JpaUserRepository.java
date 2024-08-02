package com.rafalnowak.bicycle.user.infrastructure.storage;

import com.rafalnowak.bicycle.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
