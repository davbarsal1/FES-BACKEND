package com.backend.repository;

import com.backend.model.Publicidad;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PublicidadRepository extends MongoRepository<Publicidad, String> {
    List<Publicidad> findByUsername(String username);
    List<Publicidad> findBySupervisor(String supervisor);
}
