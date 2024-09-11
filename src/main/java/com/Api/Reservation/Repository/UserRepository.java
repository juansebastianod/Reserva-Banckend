package com.Api.Reservation.Repository;

import com.Api.Reservation.Repository.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findUserById(Long id);
}