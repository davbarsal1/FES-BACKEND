package com.backend.controller;

import com.backend.model.Publicidad;
import com.backend.service.PublicidadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.*;

@RestController
@RequestMapping("/api/publicidad")
public class PublicidadController {

    private final PublicidadService publicidadService;
    private final ExecutorService executor = Executors.newCachedThreadPool();

    public PublicidadController(PublicidadService publicidadService) {
        this.publicidadService = publicidadService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody Publicidad publicidad) {
        try {
            CompletableFuture<ResponseEntity<Publicidad>> future = CompletableFuture.supplyAsync(() ->
                            ResponseEntity.ok(publicidadService.registrar(publicidad))
                    , executor).orTimeout(5, TimeUnit.SECONDS);
            return future.get();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar");
        }
    }

    @GetMapping("/por-usuario")
    public ResponseEntity<?> porUsuario(@RequestParam String username) {
        return ResponseEntity.ok(publicidadService.porUsuario(username));
    }

    @GetMapping("/por-supervisor")
    public ResponseEntity<?> porSupervisor(@RequestParam String username) {
        return ResponseEntity.ok(publicidadService.porSupervisor(username));
    }

    @GetMapping("/todas")
    public ResponseEntity<?> todas() {
        try {
            CompletableFuture<ResponseEntity<List<Publicidad>>> future = CompletableFuture.supplyAsync(() ->
                    ResponseEntity.ok(publicidadService.todas()), executor).orTimeout(5, TimeUnit.SECONDS);

            return future.get();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al obtener historial");
        }
    }

    @DeleteMapping("/reiniciar")
    public ResponseEntity<?> reiniciar() {
        publicidadService.reiniciar();
        return ResponseEntity.ok("Historial de publicidad reiniciado");
    }
}
