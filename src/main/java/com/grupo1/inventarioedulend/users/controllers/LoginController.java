package com.grupo1.inventarioedulend.users.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo1.inventarioedulend.users.models.User;
import com.grupo1.inventarioedulend.users.services.LoginService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Para que el equipo pueda probar desde el navegador
public class LoginController {

    private final LoginService loginService;

    //@Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        try {
            User user = loginService.login(email, password);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            // Devolvemos un 400 Bad Request con el mensaje "El usuario no existe" o "Contraseña incorrecta"
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}