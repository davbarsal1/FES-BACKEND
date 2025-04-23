package com.backend.service;

import com.backend.model.Publicidad;
import com.backend.repository.PublicidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublicidadService {

    private final PublicidadRepository repo;

    public PublicidadService(PublicidadRepository repo) {
        this.repo = repo;
    }

    public Publicidad registrar(Publicidad publicidad) {
        return repo.save(publicidad);
    }

    public List<Publicidad> todas() {
        return repo.findAll();
    }

    public List<Publicidad> porUsuario(String username) {
        return repo.findByUsername(username);
    }

    public List<Publicidad> porSupervisor(String supervisor) {
        return repo.findBySupervisor(supervisor);
    }

    public void reiniciar() {
        repo.deleteAll();
    }


}
