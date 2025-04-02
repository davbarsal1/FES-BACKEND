package com.backend.controller;

import com.backend.model.TiempoEnSala;
import com.backend.service.TiempoService;
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
    public TiempoEnSala iniciar(@RequestParam String username) {
        return service.iniciar(username);
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
}
