package com.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/despierta")
public class WakeUpController {

    @GetMapping
    public ResponseEntity<String> wakeUp() {
        return ResponseEntity.ok("Servidor despierto");
    }
}
