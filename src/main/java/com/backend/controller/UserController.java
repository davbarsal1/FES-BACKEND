package com.backend.controller;

import com.backend.model.User;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

@RestController
@ComponentScan
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    private final ExecutorService executor = Executors.newCachedThreadPool();

    @GetMapping("")
    public ResponseEntity<?> getAllUsers() {
        try {
            CompletableFuture<ResponseEntity<?>> future = CompletableFuture.supplyAsync(() -> {
                List<User> users = userService.getAllUsers();
                if (users == null || users.isEmpty()) {
                    return ResponseEntity.status(404).body("No se encontraron usuarios.");
                }
                return ResponseEntity.ok(users);
            }, executor).orTimeout(5, TimeUnit.SECONDS);

            return future.get();
        } catch (Exception e) {
            return ResponseEntity.status(408).body("Vuelva a intentarlo en unos segundos...");
        }
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody User user) {
        try {
            CompletableFuture<ResponseEntity<?>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    User nuevoUsuario = userService.registrarUsuario(user);
                    return ResponseEntity.ok(nuevoUsuario);
                } catch (Exception e) {
                    return ResponseEntity.badRequest().body(e.getMessage());
                }
            }, executor).orTimeout(5, TimeUnit.SECONDS);

            return future.get();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error inesperado: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        try {
            CompletableFuture<ResponseEntity<?>> future = CompletableFuture.supplyAsync(() -> {
                Optional<User> user = userService.findByUsername(loginRequest.getUsername());

                if (user.isEmpty()) {
                    return ResponseEntity.status(401).body("Credenciales incorrectas");
                }

                boolean passwordValida = userService.checkPassword(
                        loginRequest.getPassword(), user.get().getPassword()
                );

                if (!passwordValida) {
                    return ResponseEntity.status(401).body("Credenciales incorrectas");
                }

                return ResponseEntity.ok(user.get());

            }, executor).orTimeout(5, TimeUnit.SECONDS);

            return future.get();

        } catch (Exception e) {
            return ResponseEntity.status(408).body("Vuelva a intentarlo en unos segundos...");
        }
    }

    @PostMapping("/cambiarUserType")
    public ResponseEntity<?> cambiarUserType(@RequestParam String userType, @RequestParam String username) {
        try {
            CompletableFuture<ResponseEntity<String>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    userService.cambiarUserType(username, userType);
                    return ResponseEntity.ok("Status cambiado");
                } catch (Exception e) {
                    return ResponseEntity.badRequest().body(e.getMessage());
                }
            }, executor).orTimeout(5, TimeUnit.SECONDS);

            return future.get();
        } catch (Exception e) {
            return ResponseEntity.status(408).body("Vuelva a intentarlo en unos segundos...");
        }
    }

    @PostMapping("/cambiarRango")
    public ResponseEntity<?> cambiarRango(@RequestParam String rango, @RequestParam String username) {
        try {
            CompletableFuture<ResponseEntity<String>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    userService.cambiarRango(username, rango);
                    return ResponseEntity.ok("Rango cambiado");
                } catch (Exception e) {
                    return ResponseEntity.badRequest().body(e.getMessage());
                }
            }, executor).orTimeout(5, TimeUnit.SECONDS);

            return future.get();
        } catch (Exception e) {
            return ResponseEntity.status(408).body("Vuelva a intentarlo en unos segundos...");
        }
    }

    @GetMapping("/getUser")
    public ResponseEntity<?> getUser(@RequestParam String username) {
        try {
            CompletableFuture<? extends ResponseEntity<?>> future = CompletableFuture.supplyAsync(() -> {
                Optional<User> user = userService.findByUsername(username);
                return user.<ResponseEntity<?>>map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.status(404).body("Usuario no encontrado"));
            }, executor).orTimeout(5, TimeUnit.SECONDS);

            return future.get();
        } catch (Exception e) {
            return ResponseEntity.status(408).body("Vuelva a intentarlo en unos segundos...");
        }
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<?> deleteUser(@RequestParam String username) {
        try {
            CompletableFuture<? extends ResponseEntity<?>> future = CompletableFuture.supplyAsync(() -> {
                userService.deleteUser(username);
                return  ResponseEntity.ok("Usuario borrado");
            }, executor).orTimeout(5, TimeUnit.SECONDS);

            return future.get();
        } catch (Exception e) {
            return ResponseEntity.status(408).body("Vuelva a intentarlo en unos segundos...");
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll() {
        try {
            CompletableFuture<? extends ResponseEntity<?>> future = CompletableFuture.supplyAsync(() -> {
                userService.deleteAll();
                return  ResponseEntity.ok("Usuarios borrados");
            }, executor).orTimeout(5, TimeUnit.SECONDS);

            return future.get();
        } catch (Exception e) {
            return ResponseEntity.status(408).body("Vuelva a intentarlo en unos segundos...");
        }
    }

    @PostMapping("/cambiarMision")
    public ResponseEntity<?> cambiarMision(@RequestParam String rangoEspecifico, @RequestParam String username) {
        try {
            CompletableFuture<ResponseEntity<String>> future = CompletableFuture.supplyAsync(() -> {
                try {
                    userService.cambiarRangoEspecifico(username, rangoEspecifico);
                    return ResponseEntity.ok("Misión actualizada");
                } catch (Exception e) {
                    return ResponseEntity.badRequest().body(e.getMessage());
                }
            }, executor).orTimeout(5, TimeUnit.SECONDS);

            return future.get();
        } catch (Exception e) {
            return ResponseEntity.status(408).body("Vuelva a intentarlo en unos segundos...");
        }
    }

}
