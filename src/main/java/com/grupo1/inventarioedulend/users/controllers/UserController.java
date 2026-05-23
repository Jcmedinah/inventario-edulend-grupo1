package com.grupo1.inventarioedulend.users.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo1.inventarioedulend.users.dto.UserCreateDTO;
import com.grupo1.inventarioedulend.users.dto.UserDTO;
import com.grupo1.inventarioedulend.users.services.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody UserCreateDTO request) {
        return ResponseEntity.ok(userService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/by-id")
    public ResponseEntity<UserDTO> getById(@RequestParam int user_id) {
        return userService.getById(user_id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<UserDTO> update(@PathVariable int user_id, @RequestBody UserCreateDTO request) {
        UserDTO updated = userService.update(user_id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<String> delete(@PathVariable int user_id) {
        userService.delete(user_id);
        return ResponseEntity.ok("Usuario eliminado correctamente.");
    }

    @PutMapping("/{user_id}/password")
    public ResponseEntity<String> changePassword(@PathVariable int user_id, @RequestBody com.grupo1.inventarioedulend.users.dto.ChangePasswordDTO request) {
        try {
            userService.changePassword(user_id, request);
            return ResponseEntity.ok("Contraseña actualizada correctamente.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
