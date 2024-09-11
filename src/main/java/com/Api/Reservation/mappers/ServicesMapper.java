package com.Api.Reservation.mappers;

import com.Api.Reservation.Repository.Entity.Services;
import com.Api.Reservation.controller.DTO.ServicesDTO;

public class ServicesMapper {

    public static ServicesDTO toDTO(Services services) {
        ServicesDTO servicesDTO = new ServicesDTO();
        servicesDTO.setId(services.getId());
        servicesDTO.setName(services.getName());
        servicesDTO.setDescription(services.getDescription());
        servicesDTO.setPrice(services.getPrice());
        servicesDTO.setQuantity(services.getQuantity()); // Mapear la nueva propiedad
        return servicesDTO;
    }

    public static Services toEntity(ServicesDTO servicesDTO) {
        Services services = new Services();
        services.setId(servicesDTO.getId());
        services.setName(servicesDTO.getName());
        services.setDescription(servicesDTO.getDescription());
        services.setPrice(servicesDTO.getPrice());
        services.setQuantity(servicesDTO.getQuantity()); // Mapear la nueva propiedad
        return services;
    }
}