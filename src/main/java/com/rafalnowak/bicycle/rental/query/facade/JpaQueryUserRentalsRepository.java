package com.rafalnowak.bicycle.rental.query.facade;

import com.rafalnowak.bicycle.rental.command.domain.UserRentals;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface JpaQueryUserRentalsRepository extends JpaRepository<UserRentals, Integer> {
    Optional<UserRentals> findByUserId(Integer userId);
}
