package com.grupo1.inventarioedulend.users.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.grupo1.inventarioedulend.users.datasource.UserRepository;
import com.grupo1.inventarioedulend.users.dto.UserCreateDTO;
import com.grupo1.inventarioedulend.users.dto.UserDTO;
import com.grupo1.inventarioedulend.users.models.User;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO create(UserCreateDTO request) {
        // Validamos si el email ya existe antes de guardar
        Optional<User> existing = userRepository.findByEmail(request.email());
        if (existing.isPresent()) {
            throw new RuntimeException("El correo ya está registrado.");
        }
        User user = convertToEntity(request);
        return convertToDTO(userRepository.save(user));
    }

    public List<UserDTO> getAll() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<UserDTO> getById(int id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

    public UserDTO update(int id, UserCreateDTO request) {
        User existing = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        // Actualizamos los campos
        if (request.first_name() != null) existing.setFirst_name(request.first_name());
        if (request.last_name() != null) existing.setLast_name(request.last_name());
        if (request.email() != null) existing.setEmail(request.email());
        if (request.password() != null) existing.setPassword(request.password());
        if (request.phone_number() != null) existing.setPhone_number(request.phone_number());
        if (request.user_role() != null) existing.setUser_role(request.user_role());

        return convertToDTO(userRepository.save(existing));
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public void changePassword(int id, com.grupo1.inventarioedulend.users.dto.ChangePasswordDTO request) {
        User existing = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));

        if (!existing.getPassword().equals(request.currentPassword())) {
            throw new RuntimeException("Error al cambiar la contraseña. Verifica tu contraseña actual.");
        }

        if (!request.newPassword().equals(request.confirmPassword())) {
            throw new RuntimeException("Las nuevas contraseñas no coinciden.");
        }

        existing.setPassword(request.newPassword());
        userRepository.save(existing);
    }

    // --- Mapeo Manual ---

    public UserDTO convertToDTO(User user) {
        return new UserDTO(
            user.getUserId(),
            user.getFirst_name(),
            user.getLast_name(),
            user.getEmail(),
            user.getPhone_number(),
            user.getUser_role()
        );
    }

    public User convertToEntity(UserCreateDTO dto) {
        User user = new User();
        user.setFirst_name(dto.first_name());
        user.setLast_name(dto.last_name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setPhone_number(dto.phone_number());
        user.setUser_role(dto.user_role() != null ? dto.user_role() : "USER");
        return user;
    }
}