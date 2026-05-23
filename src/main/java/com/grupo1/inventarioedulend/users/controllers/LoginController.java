package com.grupo1.inventarioedulend.users.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo1.inventarioedulend.users.dto.LoginRequestDTO;
import com.grupo1.inventarioedulend.users.dto.UserDTO;
import com.grupo1.inventarioedulend.users.services.LoginService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") 
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO request) {
        try {
            UserDTO user = loginService.login(request.email(), request.password());
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}