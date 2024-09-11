package com.Api.Reservation.Repository;

import com.Api.Reservation.Repository.Entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Reservation> findByServiceId(Long serviceId);
    List<Reservation> findByCustomerId(Long customerId);
}