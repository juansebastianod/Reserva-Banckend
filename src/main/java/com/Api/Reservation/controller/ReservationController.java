package com.Api.Reservation.controller;

import com.Api.Reservation.Services.ReservationService;
import com.Api.Reservation.controller.DTO.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@CrossOrigin("*")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        ReservationDTO createdReservationDTO = reservationService.createReservation(reservationDTO);
        return ResponseEntity.ok(createdReservationDTO);
    }

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> getAllReservations() {
        List<ReservationDTO> reservationDTOs = reservationService.getAllReservations();
        return ResponseEntity.ok(reservationDTOs);
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByCustomerId(@PathVariable Long customerId) {
        List<ReservationDTO> reservationDTOs = reservationService.getReservationsByCustomerId(customerId);
        return ResponseEntity.ok(reservationDTOs);
    }

    @GetMapping("/service/{serviceId}")
    public ResponseEntity<List<ReservationDTO>> getReservationsByServiceId(@PathVariable Long serviceId) {
        List<ReservationDTO> reservationDTOs = reservationService.getReservationsByServiceId(serviceId);
        return ResponseEntity.ok(reservationDTOs);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<ReservationDTO>> getReservationsByDateRange(@RequestParam String startDate, @RequestParam String endDate) {
        List<ReservationDTO> reservationDTOs = reservationService.getReservationsByDateRange(startDate, endDate);
        return ResponseEntity.ok(reservationDTOs);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable("id") Long id, @RequestBody ReservationDTO reservationDTO) {
        ReservationDTO updatedReservationDTO = reservationService.updateReservation(id, reservationDTO);
        return updatedReservationDTO != null ? ResponseEntity.ok(updatedReservationDTO) : ResponseEntity.notFound().build();
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable("id") Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/complete/{id}")
    public ResponseEntity<ReservationDTO> completeReservation(@PathVariable("id") Long id) {
        ReservationDTO completedReservationDTO = reservationService.completeReservation(id);
        return completedReservationDTO != null ? ResponseEntity.ok(completedReservationDTO) : ResponseEntity.notFound().build();
    }


}