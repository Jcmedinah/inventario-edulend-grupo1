package com.grupo1.inventarioedulend.users.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.grupo1.inventarioedulend.users.datasource.UserRepository;
import com.grupo1.inventarioedulend.users.models.User;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        // Validamos si el email ya existe antes de guardar
        Optional<User> existing = userRepository.findByEmail(user.getEmail());
        if (existing.isPresent()) {
            throw new RuntimeException("El correo ya está registrado.");
        }
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(int id) {
        return userRepository.findById(id);
    }

    public User update(int id, User userData) {
        User existing = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        // Actualizamos los campos usando los nombres de tu modelo actual
        existing.setFirst_name(userData.getFirst_name());
        existing.setLast_name(userData.getLast_name());
        existing.setEmail(userData.getEmail());
        existing.setPhone_number(userData.getPhone_number());
        existing.setUser_role(userData.getUser_role());

        return userRepository.save(existing);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }
}