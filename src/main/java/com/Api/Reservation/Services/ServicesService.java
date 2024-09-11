package com.Api.Reservation.Services;

import com.Api.Reservation.controller.DTO.ServicesDTO;

import java.util.List;

public interface ServicesService {
    ServicesDTO createService(ServicesDTO servicesDTO);
    ServicesDTO updateService(Long id, ServicesDTO servicesDTO);
    void deleteService(Long id);

    List<ServicesDTO> getAllServices();
    ServicesDTO getServiceById(Long id);

}