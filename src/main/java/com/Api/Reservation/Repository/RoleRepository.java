package com.Api.Reservation.Repository;

import com.Api.Reservation.Repository.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // Método para buscar un rol por su nombre
    Role findByName(String name);
}