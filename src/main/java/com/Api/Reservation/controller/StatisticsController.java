package com.Api.Reservation.controller;

import com.Api.Reservation.Services.StatisticsService;
import com.Api.Reservation.controller.DTO.StatisticsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    public ResponseEntity<StatisticsDTO> getStatistics() {
        StatisticsDTO stats = statisticsService.getStatistics();
        return ResponseEntity.ok(stats);
    }
}