package com.notasprogramacion.back.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.notasprogramacion.back.rest.model.dto.Student;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Controlador para administración de estudiantes")
@RestController
@RequestMapping(value = "student")
public class StudentController {

	List<Student> students = new ArrayList<Student>();
	{
		students.add(new Student("", "Gonzalo", "IV", "México", new Date()));
		students.add(new Student("", "Marcos", "V", "USA", new Date()));
		students.add(new Student("", "Gael", "III", "México", new Date()));
		students.add(new Student("", "Marisol", "VI", "México", new Date()));
	}
	
	@ApiOperation(value = "Inserta un nuevo estudiante en el sistema ", response = Student.class, tags = "saveStudent")
	@PostMapping(value = "/", produces = "application/json")
	public Student saveStudent(@RequestBody(required=true) Student student) {
		student.setMongoId(""+students.size()+1);
		students.add(student);
		return student;
	}
	
	@ApiOperation(value = "Actualiza un estudiante en el sistema ", response = Student.class, tags = "updateStudent")
	@PutMapping(value = "/", produces = "application/json")
	public Boolean updateStudent(@RequestBody(required=true) Student student) {
		int index = 0;
		Boolean found = false;
		for(Iterator<Student> it = students.iterator(); it.hasNext();) {
			if(student.getMongoId().equals(it.next().getMongoId())) {
				found = true;
				break;
			}
			index++;
		}
		if( found ) {
			students.remove(index);
			students.add(index,student);
		}
		
		return found;
	}
	
	@ApiOperation(value = "Elimina un estudiante especifico del sistema, buscandolo por su Id ", response = Student.class, tags = "deleteStudent")
	@DeleteMapping(value = "/{studentId}", produces = "application/json")
	public Boolean deleteStudent(@PathVariable(value = "studentId") Integer studentId) {
		Integer currentSize = new Integer(students.size());
		Student current = null;
		for(Iterator<Student> it = students.iterator(); it.hasNext();) {
			current = it.next();
			if(studentId.equals(current.getMongoId())) {
				break;
			}
		}
		students.remove(current);
		Integer newSize = students.size();
		return currentSize.intValue() > newSize.intValue();
	}
	
	@ApiOperation(value = "Obtiene la lista de estudiantes del sistema ", response = Iterable.class, tags = "getStudents")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Suceess|OK"),
			@ApiResponse(code = 401, message = "not authorized!"), 
			@ApiResponse(code = 403, message = "forbidden!!!"),
			@ApiResponse(code = 404, message = "not found!!!") })
	@GetMapping(value = "/", produces = "application/json")
	public List<Student> getStudents() {
		return students;
	}

	@ApiOperation(value = "Obtiene un estudiante especifico del sistema, buscandolo por su nombre ", response = Student.class, tags = "getStudent")
	@GetMapping(value = "/getStudent/{name}", produces = "application/json")
	public Student getStudent(@PathVariable(value = "name") String name) {
		return students.stream().filter(x -> x.getName().equalsIgnoreCase(name)).collect(Collectors.toList()).get(0);
	}

	@ApiOperation(value = "Obtiene una lista de estudiantes del sistema, buscandolos por país ", response = Student.class, tags = "getStudentByCountry")
	@GetMapping(value = "/getStudentByCountry/{country}", produces = "application/json")
	public List<Student> getStudentByCountry(@PathVariable(value = "country") String country) {
		System.out.println("Searching Student in country : " + country);
		List<Student> studentsByCountry = students.stream().filter(x -> x.getCountry().equalsIgnoreCase(country))
				.collect(Collectors.toList());
		System.out.println(studentsByCountry);
		return studentsByCountry;
	}

	@ApiOperation(value = "Obtiene una lista de estudiantes del sistema, buscandolos por clase ",response = Student.class,tags="getStudentByClass")
	@GetMapping(value = "/getStudentByClass/{cls}", produces = "application/json")
	public List<Student> getStudentByClass(@PathVariable(value = "cls") String cls) {
		return students.stream().filter(x -> x.getCls().equalsIgnoreCase(cls)).collect(Collectors.toList());
	}

}
