package com.grupo1.inventarioedulend.users.services;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.grupo1.inventarioedulend.users.datasource.UserRepository;
import com.grupo1.inventarioedulend.users.dto.UserDTO;
import com.grupo1.inventarioedulend.users.models.User;

@Service
public class LoginService {

    private final UserRepository userRepository;
    private final UserService userService;

    public LoginService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public UserDTO login(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new RuntimeException("El usuario no existe");
        }

        User user = userOpt.get();

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return userService.convertToDTO(user);
    }
}