package com.Api.Reservation.Services.Impl;

import com.Api.Reservation.Repository.Entity.Reservation;
import com.Api.Reservation.Repository.Entity.Services;
import com.Api.Reservation.Repository.Entity.User;
import com.Api.Reservation.Repository.ReservationRepository;
import com.Api.Reservation.Repository.ServicesRepository;
import com.Api.Reservation.Repository.UserRepository;
import com.Api.Reservation.Services.ReservationService;
import com.Api.Reservation.controller.DTO.ReservationDTO;
import com.Api.Reservation.mappers.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Override
    public ReservationDTO createReservation(ReservationDTO reservationDTO) {
        // Buscar el cliente
        User customer = userRepository.findById(reservationDTO.getCustomerId()).orElse(null);
        if (customer == null) {
            return null;
        }
        // Buscar el servicio
        Services service = servicesRepository.findById(reservationDTO.getServiceId()).orElse(null);
        if (service == null) {
            return null;
        }
        // Verificar que la cantidad del servicio sea mayor que 0
        if (service.getQuantity() <= 0) {
            throw new IllegalArgumentException("El servicio no tiene disponibilidad.");
        }

        // Disminuir la cantidad del servicio
        service.setQuantity(service.getQuantity() - 1);
        servicesRepository.save(service);

        reservationDTO.setStatus(true);
        reservationDTO.setCompletionStatus(false);

        Reservation reservation = ReservationMapper.toEntity(reservationDTO, customer, service);
        Reservation savedReservation = reservationRepository.save(reservation);

        return ReservationMapper.toDTO(savedReservation);
    }

    @Override
    public List<ReservationDTO> getAllReservations() {
        return reservationRepository.findAll().stream()
                .filter(reservation -> Boolean.TRUE.equals(reservation.getStatus())) // Filtrar solo las reservas con status true
                .map(ReservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> getReservationsByCustomerId(Long customerId) {
        return reservationRepository.findByCustomerId(customerId).stream()
                .filter(reservation -> Boolean.TRUE.equals(reservation.getStatus())) // Filtrar solo las reservas con status true
                .map(ReservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> getReservationsByServiceId(Long serviceId) {
        return reservationRepository.findByServiceId(serviceId).stream()
                .filter(reservation -> Boolean.TRUE.equals(reservation.getStatus())) // Filtrar solo las reservas con status true
                .map(ReservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDTO> getReservationsByDateRange(String startDate, String endDate) {
        LocalDateTime start = LocalDateTime.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime end = LocalDateTime.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return reservationRepository.findByDateBetween(start, end).stream()
                .filter(reservation -> Boolean.TRUE.equals(reservation.getStatus())) // Filtrar solo las reservas con status true
                .map(ReservationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO updateReservation(Long id, ReservationDTO reservationDTO) {
        // Obtener la reserva existente
        Reservation existingReservation = reservationRepository.findById(id).orElse(null);
        if (existingReservation == null || Boolean.TRUE.equals(existingReservation.getCompletionStatus())) {
            return null; // No se puede actualizar una reserva completada
        }

        User customer = userRepository.findById(reservationDTO.getCustomerId()).orElse(null);
        if (customer == null) {
            return null;
        }
        Services service = servicesRepository.findById(reservationDTO.getServiceId()).orElse(null);
        if (service == null) {
            return null;
        }

        Reservation updatedReservation = ReservationMapper.toEntity(reservationDTO, customer, service);
        updatedReservation.setId(existingReservation.getId());

        updatedReservation.setStatus(true);
        updatedReservation = reservationRepository.save(updatedReservation);

        return ReservationMapper.toDTO(updatedReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation != null && !Boolean.TRUE.equals(reservation.getCompletionStatus())) {
            // Cambiar el status a false en lugar de eliminar la reserva
            reservation.setStatus(false);

            // Aumentar la cantidad del servicio en caso de cancelación
            Services service = reservation.getService();
            if (service != null) {
                service.setQuantity(service.getQuantity() + 1);
                servicesRepository.save(service);
            }

            reservationRepository.save(reservation);
        }
    }

    @Override
    public ReservationDTO completeReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElse(null);
        if (reservation == null || Boolean.TRUE.equals(reservation.getCompletionStatus())) {
            return null; // Reserva no encontrada o ya completada
        }

        reservation.setCompletionStatus(true);
        reservation.setStatus(false); // No se puede editar ni cancelar después de completada

        // Aumentar la cantidad del servicio en caso de completación
        Services service = reservation.getService();
        if (service != null) {
            service.setQuantity(service.getQuantity() + 1);
            servicesRepository.save(service);
        }

        Reservation updatedReservation = reservationRepository.save(reservation);
        return ReservationMapper.toDTO(updatedReservation);
    }
}