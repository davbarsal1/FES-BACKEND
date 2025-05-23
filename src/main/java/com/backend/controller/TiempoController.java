package com.backend.controller;

import com.backend.model.TiempoEnSala;
import com.backend.service.TiempoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tiempo")
public class TiempoController {

    private final TiempoService service;

    public TiempoController(TiempoService service) {
        this.service = service;
    }

    @PostMapping("/iniciar")
    public TiempoEnSala iniciar(@RequestParam String username, @RequestParam String iniciadoPor) {
        return service.iniciar(username, iniciadoPor);
    }

    @PostMapping("/estado")
    public ResponseEntity<?> actualizarEstado(@RequestParam String username, @RequestParam String estado) {
        service.actualizarEstado(username, estado);
        return ResponseEntity.ok("Estado actualizado");
    }

    @PostMapping("/detener")
    public TiempoEnSala detener(@RequestParam String username) {
        return service.detener(username);
    }


    @GetMapping("/usuario")
    public TiempoEnSala getTiempoUsuario(@RequestParam String username) {
        return service.obtenerTiempo(username);
    }

    @GetMapping("/todos")
    public List<TiempoEnSala> getTodos() {
        return service.obtenerTodos();
    }

    @DeleteMapping("/reiniciar")
    public ResponseEntity<?> reiniciar() {
        service.reiniciar();
        return ResponseEntity.ok("Borrado exitosamente");
    }

    @PostMapping("/anadirTiempo")
    public ResponseEntity<?> añadirTiempo(@RequestParam String username, @RequestParam long segundos){
        service.anadirTiempo(username,segundos);
        return ResponseEntity.ok("Añadido exitosamente");
    }
}
