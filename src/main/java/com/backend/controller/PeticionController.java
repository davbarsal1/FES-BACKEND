package com.backend.controller;

import com.backend.model.EstadoPeticion;
import com.backend.model.Peticion;
import com.backend.service.PeticionService;
import com.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/peticion")
public class PeticionController {

    private final PeticionService peticionService;

    public PeticionController(PeticionService peticionService) {
        this.peticionService = peticionService;
    }

    @Autowired
    UserService userService;
    @PostMapping("/crear")
    public ResponseEntity<?> crearPeticion(@RequestBody Peticion peticion) {
        try {
            Peticion nueva = peticionService.crearPeticion(peticion);
            return ResponseEntity.ok(nueva);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar petición: " + e.getMessage());
        }
    }

    @GetMapping("/todas")
    public ResponseEntity<List<Peticion>> obtenerPeticiones() {
        return ResponseEntity.ok(peticionService.obtenerTodas());
    }

    @PostMapping("/aceptar")
    public ResponseEntity<?> aceptar(@RequestParam String id) {
        try {
            Optional<Peticion> peticion = peticionService.obtenerPorId(id);
            if (peticion.isEmpty()) return ResponseEntity.notFound().build();

            Peticion p = peticion.get();

            if (p.getUserType() != null) {
                userService.cambiarUserType(p.getUsername(), p.getUserType().name());
            }

            if (p.getRango() != null) {
                userService.cambiarRango(p.getUsername(), p.getRango().name());
            }

            peticionService.cambiarEstado(id, EstadoPeticion.APROBADA);
            return ResponseEntity.ok("Petición aprobada");

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al aceptar: " + e.getMessage());
        }
    }

    @PostMapping("/rechazar")
    public ResponseEntity<?> rechazar(@RequestParam String id) {
        try {
            peticionService.cambiarEstado(id, EstadoPeticion.RECHAZADA);
            return ResponseEntity.ok("Petición rechazada");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al rechazar: " + e.getMessage());
        }
    }

    @GetMapping("/por-usuario")
    public ResponseEntity<?> obtenerPorUsuario(@RequestParam String username) {
        try {
            List<Peticion> lista = peticionService.obtenerPorUsername(username);
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

}
