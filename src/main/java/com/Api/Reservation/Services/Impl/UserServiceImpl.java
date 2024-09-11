package com.Api.Reservation.Services.Impl;

import com.Api.Reservation.Services.Token;
import com.Api.Reservation.controller.DTO.UserDTO;
import com.Api.Reservation.Repository.Entity.Role;
import com.Api.Reservation.Repository.Entity.User;
import com.Api.Reservation.Repository.RoleRepository;
import com.Api.Reservation.Repository.UserRepository;
import com.Api.Reservation.Services.UserService;
import com.Api.Reservation.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public String login( String username, String password) throws Exception {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new Exception("Usuario no encontrado");
        }

        if (!user.getPassword().equals(password)) {
            throw new Exception("Contraseña incorrecta");
        }

        Token token =new Token(user.getId(),user.getRole().getId());

        return  token.toJsonString();

    }

    @Override
    public UserDTO saveUser(UserDTO userDTO) throws Exception {
        User existingUser = userRepository.findByUsername(userDTO.getUsername());
        if (existingUser != null) {
            throw new Exception("El usuario ya existe");
        }

        Role role = roleRepository.findById(userDTO.getRoleId())
                .orElseThrow(() -> new Exception("Rol no encontrado"));

        User user = UserMapper.toEntity(userDTO, role);
        User savedUser = userRepository.save(user);

        return UserMapper.toDTO(savedUser, savedUser.getRole().getId());
    }

    @Override
    public List<UserDTO> getAllUsers() throws Exception {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> UserMapper.toDTO(user, user.getRole().getId()))
                .collect(Collectors.toList());
    }


    @Override
    public UserDTO updateUser(UserDTO userDTO) throws Exception {
        Optional<User> optionalUser = userRepository.findById(userDTO.getId());
        if (!optionalUser.isPresent()) {
            throw new Exception("Usuario no encontrado");
        }
        User user = optionalUser.get();
        Role role= new Role();
        role.setId(2L);
        User updatedUser = UserMapper.toEntity(userDTO, role);
        updatedUser.setId(user.getId()); // Mantén el ID del usuario existente
        updatedUser = userRepository.save(updatedUser);

        // Convierte la entidad actualizada de nuevo a DTO
        return UserMapper.toDTO(updatedUser, role.getId());
    }
    @Override
    public void deleteUserById(Long userId) throws Exception {
        System.out.println("soy un id "+userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));
        userRepository.delete(user);
        System.out.println("user "+user.getId());
    }

    @Override
    public UserDTO getUserById(Long userId) throws Exception {
        return null;
    }

    @Override
    public boolean validToken(Token token) {
        try {
            User user = userRepository.findById(token.getUserId())
                    .orElseThrow(() -> new Exception("Usuario no encontrado"));

            Role role = roleRepository.findById(token.getRolId())
                    .orElseThrow(() -> new Exception("Rol no encontrado"));
            return true;

        } catch (Exception e) {
            // En caso de error, el token no es válido
            return false;
        }
    }
}