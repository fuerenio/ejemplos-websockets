package com.notasprogramacion.websockets.server.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.notasprogramacion.websockets.server.model.Persona;

@Repository
public interface PersonaRepository extends MongoRepository<Persona, Integer> {

}
