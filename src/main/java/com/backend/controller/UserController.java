package com.backend.controller;

import com.backend.model.User;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@ComponentScan
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody User user) {
        try {
            User nuevoUsuario = userService.registrarUsuario(user);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Login de usuario
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        Optional<User> user = userService.findByEmail(loginRequest.getEmail());

        if (user.isEmpty() || !userService.checkPassword(loginRequest.getPassword(), user.get().getPassword())) {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }

        return ResponseEntity.ok("Login exitoso");
    }
}
