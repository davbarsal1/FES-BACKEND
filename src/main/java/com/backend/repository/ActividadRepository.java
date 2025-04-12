package com.backend.repository;

import com.backend.model.Actividad;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActividadRepository extends MongoRepository<Actividad, String> {
}
