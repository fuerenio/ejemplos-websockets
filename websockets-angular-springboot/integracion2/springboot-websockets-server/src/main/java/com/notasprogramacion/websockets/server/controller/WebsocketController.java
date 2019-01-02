package com.notasprogramacion.websockets.server.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.notasprogramacion.websockets.server.dto.PersonaDto;
import com.notasprogramacion.websockets.server.service.PersonaService;

@Controller
@RequestMapping("/websocket")
public class WebsocketController {
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	@PostMapping("/broadcast")
    public ResponseEntity<Boolean> broadcastStudent(@RequestBody PersonaDto personaDto) throws Exception {
		System.out.println(personaDto.toString());
		simpMessagingTemplate.convertAndSend("/topic/broadcastSubscribe", personaDto);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }
	
	@MessageMapping("/message")
    public void processMessageFromClient(@Payload PersonaDto personaDto, SimpMessageHeaderAccessor  headerAccessor) throws Exception {
		String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
		System.out.println("WebSocketController > "+sessionId+" envio el mensaje: "+personaDto.toString());
		PersonaDto personaResponse = personaService.save(personaDto);
		//System.out.println("WebSocketController > "+sessionId+" envio el mensaje: "+new Gson().fromJson(message, Map.class).get("name"));
		headerAccessor.setSessionId(sessionId);
		simpMessagingTemplate.convertAndSend("/topic/broadcastSubscribe", personaResponse);
      
    }
}
