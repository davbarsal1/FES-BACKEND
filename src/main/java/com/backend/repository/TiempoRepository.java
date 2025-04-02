package com.backend.repository;

import com.backend.model.TiempoEnSala;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TiempoRepository extends MongoRepository<TiempoEnSala, String> {
    Optional<TiempoEnSala> findByUsername(String username);
}
