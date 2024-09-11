package com.Api.Reservation.controller;

import com.Api.Reservation.Services.ServicesService;
import com.Api.Reservation.controller.DTO.ServicesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
@CrossOrigin("*")
public class ServicesController {

    @Autowired
    private ServicesService servicesService;

    @PostMapping
    public ResponseEntity<ServicesDTO> createService(@RequestBody ServicesDTO servicesDTO) {
        ServicesDTO createdServiceDTO = servicesService.createService(servicesDTO);
        return new ResponseEntity<>(createdServiceDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicesDTO> updateService(@PathVariable Long id, @RequestBody ServicesDTO servicesDTO) {
        ServicesDTO updatedServiceDTO = servicesService.updateService(id, servicesDTO);
        if (updatedServiceDTO != null) {
            return new ResponseEntity<>(updatedServiceDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        servicesService.deleteService(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping
    public ResponseEntity<List<ServicesDTO>> getAllServices() {
        List<ServicesDTO> servicesDTOList = servicesService.getAllServices();
        return new ResponseEntity<>(servicesDTOList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicesDTO> getServiceById(@PathVariable Long id) {
        try {
            ServicesDTO service = servicesService.getServiceById(id);
            return new ResponseEntity<>(service, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}