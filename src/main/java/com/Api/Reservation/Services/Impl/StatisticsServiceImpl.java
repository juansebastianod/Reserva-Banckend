package com.Api.Reservation.Services.Impl;

import com.Api.Reservation.Repository.ReservationRepository;
import com.Api.Reservation.Repository.ServicesRepository;
import com.Api.Reservation.Repository.Entity.Reservation;
import com.Api.Reservation.Repository.Entity.Services;
import com.Api.Reservation.Services.StatisticsService;
import com.Api.Reservation.controller.DTO.StatisticsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Override
    public StatisticsDTO getStatistics() {
        StatisticsDTO stats = new StatisticsDTO();

        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.minusWeeks(1);
        LocalDateTime startOfDay = startOfWeek.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);

        // Total ingresos de servicios completados
        Double totalIngresos = reservationRepository.findByDateBetween(startOfDay, endOfDay).stream()
                .filter(reservation -> Boolean.TRUE.equals(reservation.getCompletionStatus())
                        && Boolean.TRUE.equals(reservation.getStatus())) // Filtra por reservas completadas y no canceladas
                .map(reservation -> {
                    Services service = reservation.getService();
                    return service != null ? service.getPrice() : 0.0;
                })
                .mapToDouble(Double::doubleValue)
                .sum();
        stats.setTotalIngresos(totalIngresos);

        // Total ventas (reservas completadas)
        Integer totalVentas = (int) reservationRepository.findByDateBetween(startOfDay, endOfDay).stream()
                .filter(reservation -> Boolean.TRUE.equals(reservation.getCompletionStatus())
                        && Boolean.TRUE.equals(reservation.getStatus())) // Filtra por reservas completadas y no canceladas
                .count();
        stats.setTotalVentas(totalVentas);

        // Total productos (servicios)
        Integer totalProductos = (int) servicesRepository.count();
        stats.setTotalProductos(totalProductos);

        // Reservas en la semana
        Integer reservasSemana = (int) reservationRepository.findByDateBetween(startOfDay, endOfDay).size();
        stats.setReservasSemana(reservasSemana);

        return stats;
    }
}