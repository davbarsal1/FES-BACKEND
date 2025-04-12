package com.backend.controller;

import com.backend.model.Actividad;
import com.backend.service.ActividadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/actividad")
public class ActividadController {

    private final ActividadService actividadService;

    public ActividadController(ActividadService actividadService) {
        this.actividadService = actividadService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Actividad actividad) {
        return ResponseEntity.ok(actividadService.registrar(actividad));
    }

    @GetMapping("/pdas")
    public ResponseEntity<?> getPDAResumen() {
        return ResponseEntity.ok(actividadService.obtenerPDAsTotales());
    }

    @GetMapping("/por-usuario")
    public ResponseEntity<?> porUsuario(@RequestParam String username) {
        return ResponseEntity.ok(actividadService.getPorUsuario(username));
    }

    @DeleteMapping("/reiniciar")
    public ResponseEntity<?> reiniciar() {
        actividadService.reiniciar();
        return ResponseEntity.ok("Actividades reiniciadas");
    }
}
