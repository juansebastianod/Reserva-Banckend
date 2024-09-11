package com.Api.Reservation.mappers;

import com.Api.Reservation.Repository.Entity.Role;
import com.Api.Reservation.controller.DTO.UserDTO;
import com.Api.Reservation.Repository.Entity.User;

public class UserMapper {

    public static UserDTO toDTO(User user,Long role) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setAddress(user.getAddress());
        userDTO.setRoleId(user.getRole().getId());
        userDTO.setRoleId(role);
        return userDTO;
    }

    public static User toEntity(UserDTO userDTO, Role role) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setRole(role);

        return user;
    }
}