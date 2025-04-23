package com.backend.service;

import com.backend.model.RANGO;
import com.backend.model.User;
import com.backend.model.UserType;
import com.backend.model.Ventaja;
import com.backend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String encriptar(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public User registrarUsuario(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("El correo ya está registrado");
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya está en uso");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserType(UserType.EMPLEADO);
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void cambiarUserType(String username, String userType) {
        Optional<User> userChanged = findByUsername(username);
        userChanged.ifPresent(value -> value.setUserType(UserType.valueOf(userType)));
        userChanged.ifPresent(userRepository::save);
    }

    public void cambiarRangoEspecifico(String username, String rangoEspecifico) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setRangoEspecifico(rangoEspecifico);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }
    }

    public void cambiarRango(String username, String rango) {
        Optional<User> userChanged = findByUsername(username);
        userChanged.ifPresent(value -> value.setRango(RANGO.valueOf(rango)));
        userChanged.ifPresent(userRepository::save);
    }

    public void deleteUser(String username) {

        userRepository.delete(findByUsername(username).get());
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }

    public void agregarVentaja(String username, String nombreVentaja) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Ventaja ventaja = Ventaja.valueOf(nombreVentaja.toUpperCase());
        if (user.getVentajas() == null) {
            user.setVentajas(new HashMap<>());
        }
        user.getVentajas().put(ventaja, LocalDate.now());
        userRepository.save(user);
    }

    public void eliminarVentaja(String username, String nombreVentaja) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Ventaja ventaja = Ventaja.valueOf(nombreVentaja.toUpperCase());
        if (user.getVentajas() != null) {
            user.getVentajas().remove(ventaja);
        }
        userRepository.save(user);
    }


}
