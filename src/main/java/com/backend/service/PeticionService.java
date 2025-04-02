package com.backend.service;

import com.backend.model.EstadoPeticion;
import com.backend.model.Peticion;
import com.backend.repository.PeticionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeticionService {

    private final PeticionRepository peticionRepository;

    public PeticionService(PeticionRepository peticionRepository) {
        this.peticionRepository = peticionRepository;
    }

    public Peticion crearPeticion(Peticion peticion) {
        return peticionRepository.save(peticion);
    }

    public List<Peticion> obtenerTodas() {
        return peticionRepository.findAll();
    }

    public void eliminarPorId(String id) {
        peticionRepository.deleteById(id);
    }

    public List<Peticion> obtenerPorUsername(String username) {
        return peticionRepository.findByUsername(username);
    }

    public void cambiarEstado(String id, EstadoPeticion nuevoEstado) {
        Optional<Peticion> peticion = peticionRepository.findById(id);
        peticion.ifPresent(p -> {
            p.setEstado(nuevoEstado);
            peticionRepository.save(p);
        });
    }


    public Optional<Peticion> obtenerPorId(String id) {
        return peticionRepository.findById(id);
    }
}
