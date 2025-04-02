package com.backend.repository;

import com.backend.model.Peticion;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PeticionRepository extends MongoRepository<Peticion, String> {
    List<Peticion> findByUsername(String username);
}

