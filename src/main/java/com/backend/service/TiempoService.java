package com.backend.service;

import com.backend.model.TiempoEnSala;
import com.backend.repository.TiempoRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TiempoService {

    private final TiempoRepository repo;

    public TiempoService(TiempoRepository repo) {
        this.repo = repo;
    }

    public TiempoEnSala iniciar(String username, String iniciadoPor) {
        Optional<TiempoEnSala> activo = repo.findByUsernameAndActivoTrue(username);
        if (activo.isPresent()) {
            throw new RuntimeException("Ya hay un tiempo activo para este usuario");
        }

        TiempoEnSala nuevo = new TiempoEnSala();
        nuevo.setUsername(username);
        nuevo.setInicio(LocalDateTime.now());
        nuevo.setActivo(true);
        nuevo.setIniciadoPor(iniciadoPor); // 👈 Aquí se guarda el iniciador

        return repo.save(nuevo);
    }



    public TiempoEnSala detener(String username) {
        Optional<TiempoEnSala> tiempoOpt = repo.findByUsernameAndActivoTrue(username);

        if (tiempoOpt.isPresent()) {
            TiempoEnSala tiempo = tiempoOpt.get();

            if (tiempo.isActivo() && tiempo.getInicio() != null) {
                long segundos = Duration.between(tiempo.getInicio(), LocalDateTime.now()).getSeconds();
                tiempo.setSegundosTotales(tiempo.getSegundosTotales() + segundos);
            }

            tiempo.setActivo(false);
            tiempo.setInicio(null);
            return repo.save(tiempo);
        }

        throw new RuntimeException("No se encontró sesión activa.");
    }

    public TiempoEnSala obtenerTiempo(String username) {
        return repo.findByUsernameAndActivoTrue(username).orElse(null);
    }

    public List<TiempoEnSala> obtenerTodos() {
        return repo.findAll();
    }
}
