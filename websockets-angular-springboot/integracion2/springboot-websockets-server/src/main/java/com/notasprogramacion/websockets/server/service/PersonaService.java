package com.notasprogramacion.websockets.server.service;

import java.util.List;

import com.notasprogramacion.websockets.server.dto.PersonaDto;

public interface PersonaService {
	
	public PersonaDto save(PersonaDto nuevaPersona);
	
	public List<PersonaDto> getAll();
}
