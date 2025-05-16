package com.backend.service;

import com.backend.model.TiempoEnSala;
import com.backend.repository.TiempoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(TiempoService.class);

    public TiempoEnSala iniciar(String username, String iniciadoPor) {
        TiempoEnSala tiempo = repo.findByUsername(username).orElse(new TiempoEnSala());
        tiempo.setUsername(username);
        tiempo.setInicio(LocalDateTime.now());
        tiempo.setActivo(true);
        tiempo.setIniciadoPor(iniciadoPor);
        tiempo.setEstado("NORMAL");
        return repo.save(tiempo);
    }

    public void actualizarEstado(String username, String estado) {
        TiempoEnSala tiempo = repo.findByUsername(username).orElse(new TiempoEnSala());
        tiempo.setUsername(username);
        tiempo.setEstado(estado);
        repo.save(tiempo);
    }


    public TiempoEnSala detener(String username) {
        Optional<TiempoEnSala> tiempoOpt = repo.findByUsername(username);

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
        return repo.findByUsername(username).orElse(null);
    }

    public List<TiempoEnSala> obtenerTodos() {
        return repo.findAll();
    }

    public TiempoEnSala cambiarEstado(String username, String estado) {
        TiempoEnSala tiempo = repo.findByUsername(username).orElseThrow(() ->
                new RuntimeException("Usuario no encontrado"));

        tiempo.setEstado(estado);
        return repo.save(tiempo);
    }

    public void reiniciar() {
        repo.deleteAll();
    }

    public void añadirTiempo(String username, long segundos ){

        for(TiempoEnSala t: repo.findAll()){
            if(t.getUsername().equals(username)){
                t.setSegundosTotales(segundos);
                repo.save(t);
            }
        }

    }
}
