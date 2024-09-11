package com.Api.Reservation.mappers;

import com.Api.Reservation.Repository.Entity.Reservation;
import com.Api.Reservation.Repository.Entity.Services;
import com.Api.Reservation.Repository.Entity.User;
import com.Api.Reservation.controller.DTO.ReservationDTO;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class ReservationMapper {

    // Formato de fecha para dd/MM/yyyy
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Conversión de DTO a Entidad
    public static Reservation toEntity(ReservationDTO reservationDTO, User customer, Services service) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDTO.getId());
        reservation.setCustomer(customer);
        reservation.setService(service);

        // Convertir String a Date
        try {
            LocalDate localDate = LocalDate.parse(reservationDTO.getDate(), DATE_FORMATTER);
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            reservation.setDate(date);
        } catch (DateTimeParseException e) {
            System.err.println("Error al convertir la fecha: " + reservationDTO.getDate() + ". Formato esperado: dd/MM/yyyy");
            e.printStackTrace();
            throw e;  // O manejar la excepción según sea necesario
        }

        reservation.setTime(reservationDTO.getTime());
        reservation.setStatus(reservationDTO.getStatus());
        reservation.setNumberOfGuests(reservationDTO.getNumberOfGuests());
        reservation.setCompletionStatus(reservationDTO.getCompletionStatus()); // Nuevo campo añadido
        return reservation;
    }

    // Conversión de Entidad a DTO
    public static ReservationDTO toDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setId(reservation.getId());
        reservationDTO.setCustomerId(reservation.getCustomer().getId());
        reservationDTO.setServiceId(reservation.getService().getId());

        LocalDate localDate = reservation.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        reservationDTO.setDate(localDate.format(DATE_FORMATTER));

        reservationDTO.setTime(reservation.getTime());
        reservationDTO.setStatus(reservation.getStatus());
        reservationDTO.setNumberOfGuests(reservation.getNumberOfGuests());
        reservationDTO.setCompletionStatus(reservation.getCompletionStatus()); // Nuevo campo añadido
        return reservationDTO;
    }
}