package com.notasprogramacion.websockets.server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.notasprogramacion.websockets.server.dto.PersonaDto;
import com.notasprogramacion.websockets.server.service.PersonaService;

@Controller
@RequestMapping("/persona")
public class PersonaController {
	
	@Autowired
	private PersonaService personaService;
	
	@PostMapping("/save")
    public ResponseEntity<PersonaDto> save(@RequestBody PersonaDto personaDto) throws Exception {
		System.out.println(personaDto.toString());
		PersonaDto personaResponse = personaService.save(personaDto);
		return new ResponseEntity<PersonaDto>(personaResponse, HttpStatus.OK);
    }
	
	@GetMapping("/getAll")
    public ResponseEntity<List<PersonaDto>> getAll() throws Exception {
		List<PersonaDto> personasResponse = personaService.getAll();
		return new ResponseEntity<List<PersonaDto>>(personasResponse, HttpStatus.OK);
    }
}
