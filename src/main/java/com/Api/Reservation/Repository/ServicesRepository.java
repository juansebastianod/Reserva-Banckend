package com.Api.Reservation.Repository;

import com.Api.Reservation.Repository.Entity.Services;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicesRepository extends JpaRepository<Services, Long> {
}