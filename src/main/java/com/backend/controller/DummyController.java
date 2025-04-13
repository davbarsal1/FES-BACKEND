package com.backend.controller;


import com.backend.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/dummy")
public class DummyController {

    private final ExecutorService executor = Executors.newCachedThreadPool();
    @GetMapping("")
    public ResponseEntity<?> dummy(){
        try {
            CompletableFuture<? extends ResponseEntity<?>> future = CompletableFuture.supplyAsync(() ->
                    ResponseEntity.ok("Servicio activo"), executor).orTimeout(5, TimeUnit.SECONDS);

            return future.get();
        } catch (Exception e) {
            return ResponseEntity.status(408).body("Vuelva a intentarlo en unos segundos...");
        }
    }
}
