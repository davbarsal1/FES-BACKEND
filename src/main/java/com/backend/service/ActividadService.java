package com.backend.service;

import com.backend.model.Actividad;
import com.backend.repository.ActividadRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ActividadService {
    private final ActividadRepository repo;

    public ActividadService(ActividadRepository repo) {
        this.repo = repo;
    }

    public Actividad registrar(Actividad actividad) {
        return repo.save(actividad);
    }

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


    public void reiniciar() {
        repo.deleteAll();
    }
}
