package com.rafalnowak.bicycle.rental.command.infrastructure.storage;

import com.rafalnowak.bicycle.rental.command.domain.UserRentals;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface JpaUserRentalsRepository extends JpaRepository<UserRentals, Integer> {
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    Optional<UserRentals> findByUserId(Integer userId);
}
