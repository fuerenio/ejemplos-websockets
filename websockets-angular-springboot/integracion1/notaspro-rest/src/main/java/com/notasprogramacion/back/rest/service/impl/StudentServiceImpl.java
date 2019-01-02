package com.notasprogramacion.back.rest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notasprogramacion.back.rest.model.dto.Student;
import com.notasprogramacion.back.rest.repository.StudentRepository;
import com.notasprogramacion.back.rest.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public Student save(Student newStudent) {
		return studentRepository.insert(newStudent);
	}

}
