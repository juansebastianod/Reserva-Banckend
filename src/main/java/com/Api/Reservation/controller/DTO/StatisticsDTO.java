package com.Api.Reservation.controller.DTO;

public class StatisticsDTO {
    private Double totalIngresos;
    private Integer totalVentas;
    private Integer totalProductos;
    private Integer reservasSemana;

    // Getters y Setters
    public Double getTotalIngresos() {
        return totalIngresos;
    }

    public void setTotalIngresos(Double totalIngresos) {
        this.totalIngresos = totalIngresos;
    }

    public Integer getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(Integer totalVentas) {
        this.totalVentas = totalVentas;
    }

    public Integer getTotalProductos() {
        return totalProductos;
    }

    public void setTotalProductos(Integer totalProductos) {
        this.totalProductos = totalProductos;
    }

    public Integer getReservasSemana() {
        return reservasSemana;
    }

    public void setReservasSemana(Integer reservasSemana) {
        this.reservasSemana = reservasSemana;
    }
}