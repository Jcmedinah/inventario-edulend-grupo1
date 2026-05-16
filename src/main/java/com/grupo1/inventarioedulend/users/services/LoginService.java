package com.grupo1.inventarioedulend.users.services;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.grupo1.inventarioedulend.users.datasource.UserRepository;
import com.grupo1.inventarioedulend.users.models.User;

@Service
public class LoginService {

    private final UserRepository userRepository;

    // Constructor manual para mantener la consistencia con el equipo
    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String email, String password) {
        // Buscamos al usuario por su email
        Optional<User> userOpt = userRepository.findByEmail(email);

        if (userOpt.isEmpty()) {
            throw new RuntimeException("El usuario no existe");
        }

        User user = userOpt.get();

        // Verificamos la contraseña (texto plano por ahora, como en tu código original)
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return user;
    }
}