package com.notasprogramacion.back.rest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.notasprogramacion.back.rest.model.dto.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, Integer> {

}
