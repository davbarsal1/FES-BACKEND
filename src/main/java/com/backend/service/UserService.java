package com.backend.service;

import com.backend.model.User;
import com.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User registrarUsuario(User user) {
        // Verificar si el email ya está registrado
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        // Verificar si el username ya está en uso
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        // Encriptar la contraseña antes de guardarla
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Guardar usuario en la base de datos
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

}
