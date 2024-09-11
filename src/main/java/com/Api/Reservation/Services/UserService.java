package com.Api.Reservation.Services;

import com.Api.Reservation.controller.DTO.UserDTO;
import com.Api.Reservation.Repository.Entity.User;

import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO) throws Exception;
    List<UserDTO> getAllUsers() throws Exception;
    UserDTO updateUser(UserDTO userDTO) throws Exception;
    void deleteUserById(Long userId) throws Exception;
    UserDTO getUserById(Long userId) throws Exception;

    boolean validToken(Token token) throws Exception;

    String login(String username, String password) throws Exception;



}