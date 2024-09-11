package com.Api.Reservation.Services;

import com.Api.Reservation.controller.DTO.ReservationDTO;

import java.util.List;

public interface ReservationService {
    ReservationDTO createReservation(ReservationDTO reservationDTO);

    List<ReservationDTO> getAllReservations();
    List<ReservationDTO> getReservationsByCustomerId(Long customerId);
    List<ReservationDTO> getReservationsByServiceId(Long serviceId);
    List<ReservationDTO> getReservationsByDateRange(String startDate, String endDate);
    ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO);
    void deleteReservation(Long id);
    ReservationDTO completeReservation(Long id); // Nuevo método para completar una reserva
}