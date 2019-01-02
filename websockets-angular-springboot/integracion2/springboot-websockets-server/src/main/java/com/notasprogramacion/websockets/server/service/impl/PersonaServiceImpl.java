package com.notasprogramacion.websockets.server.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.notasprogramacion.websockets.server.dto.PersonaDto;
import com.notasprogramacion.websockets.server.model.Persona;
import com.notasprogramacion.websockets.server.repository.PersonaRepository;
import com.notasprogramacion.websockets.server.service.PersonaService;

@Service("personaService")
public class PersonaServiceImpl implements PersonaService {

	@Autowired
	private PersonaRepository personaRepository;

    @Autowired
    private ModelMapper modelMapper;
    
	@Override
	public PersonaDto save(PersonaDto nuevaPersonaDto) {
		Persona persona = modelMapper.map(nuevaPersonaDto, Persona.class);
		nuevaPersonaDto = modelMapper.map(personaRepository.insert(persona), PersonaDto.class);
		return nuevaPersonaDto;
	}

	@Override
	public List<PersonaDto> getAll() {
		return personaRepository.findAll().stream().map((value) -> {
			return modelMapper.map(value, PersonaDto.class);
		}).collect(Collectors.toList());
	}

}
