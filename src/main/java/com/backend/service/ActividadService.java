package com.backend.service;

import com.backend.model.Actividad;
import com.backend.model.TiempoEnSala;
import com.backend.repository.ActividadRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ActividadService {
    private final ActividadRepository repo;

    public ActividadService(ActividadRepository repo) {
        this.repo = repo;
    }

    public Actividad registrar(Actividad actividad) {
        return repo.save(actividad);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ActividadService.class);
    public List<Actividad> getPorUsuario(String username) {
        return repo.findAll().stream()
                .filter(a -> username.equals(a.getGuia()) || a.getParticipantes().containsKey(username))
                .collect(Collectors.toList());
    }


    public Map<String, Double> obtenerPDAsTotales() {
        Map<String, Double> pdaMap = new HashMap<>();
        for (Actividad a : repo.findAll()) {
            pdaMap.merge(a.getGuia(), 1.0, Double::sum); // El guía siempre 1
            for (Map.Entry<String, Double> entry : a.getParticipantes().entrySet()) {
                pdaMap.merge(entry.getKey(), entry.getValue(), Double::sum);
            }
        }
        return pdaMap;
    }

    public Double obtenerPDAsPorUsuario(String username) {
        double pdas = 0.0;
        for (Actividad a : repo.findAll()) {
            Map<String, Double> participantes = a.getParticipantes();
            if (participantes.containsKey(username)) {
                Double pdaConseguido = participantes.get(username);
                pdas += (pdaConseguido != null) ? pdaConseguido : 0.0;
            }
            if(a.getGuia().equals(username)){
                pdas += 1.0;
            }
        }

        return pdas;
    }

    public void reiniciar() {
        repo.deleteAll();
    }

}
