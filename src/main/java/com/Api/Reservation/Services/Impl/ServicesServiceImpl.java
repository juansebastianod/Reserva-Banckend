package com.Api.Reservation.Services.Impl;

import com.Api.Reservation.Repository.Entity.Services;
import com.Api.Reservation.Repository.ServicesRepository;
import com.Api.Reservation.Services.ServicesService;
import com.Api.Reservation.controller.DTO.ServicesDTO;
import com.Api.Reservation.mappers.ServicesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServicesServiceImpl implements ServicesService {

    @Autowired
    private ServicesRepository servicesRepository;

    @Override
    public ServicesDTO createService(ServicesDTO servicesDTO) {
        Services service = ServicesMapper.toEntity(servicesDTO);
        Services savedService = servicesRepository.save(service);
        return ServicesMapper.toDTO(savedService);
    }

    @Override
    public ServicesDTO updateService(Long id, ServicesDTO servicesDTO) {
        Optional<Services> optionalService = servicesRepository.findById(id);
        if (optionalService.isPresent()) {
            Services service = ServicesMapper.toEntity(servicesDTO);
            service.setId(id);
            Services updatedService = servicesRepository.save(service);
            return ServicesMapper.toDTO(updatedService);
        }
        return null; // O lanzar una excepción si se prefiere
    }

    @Override
    public void deleteService(Long id) {
        servicesRepository.deleteById(id);
    }



    @Override
    public List<ServicesDTO> getAllServices() {
        return servicesRepository.findAll().stream()
                .map(ServicesMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ServicesDTO getServiceById(Long id) {
        Services service = servicesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado")); // O lanzar una excepción personalizada
        return ServicesMapper.toDTO(service);
    }
}