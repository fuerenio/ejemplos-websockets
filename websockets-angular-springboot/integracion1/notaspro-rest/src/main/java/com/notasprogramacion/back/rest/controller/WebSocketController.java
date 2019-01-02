package com.notasprogramacion.back.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.notasprogramacion.back.rest.model.dto.MensajeDto;
import com.notasprogramacion.back.rest.model.dto.Student;
import com.notasprogramacion.back.rest.service.StudentService;

@CrossOrigin(origins = "http://localhost:4200")
@Controller
@RequestMapping("/socket")
public class WebSocketController {
	
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	@Autowired
	private StudentService studentService;
	
	//revisar https://www.devglan.com/spring-boot/spring-session-stomp-websocket
	@PostMapping("/broadcastMsg")
    public ResponseEntity<MensajeDto> broadcastMensaje(@RequestBody MensajeDto mensajeDto/*, SimpMessageHeaderAccessor  simpMessageHeaderAccessor*/) throws Exception {
		//String sessionId = headerAccessor.getSessionAttributes().get("sessionId").toString();
		//System.out.println("sessionId: "+sessionId);
		System.out.println(mensajeDto.toString());
		simpMessagingTemplate.convertAndSend("/topic/testSubscribe", mensajeDto);
		return new ResponseEntity<MensajeDto>(mensajeDto, HttpStatus.OK);
    }
	
	@PostMapping("/broadcastStudent")
    public ResponseEntity<Student> broadcastStudent(@RequestBody Student student) throws Exception {
		System.out.println(student.toString());
		studentService.save(student);
		simpMessagingTemplate.convertAndSend("/topic/testSubscribe", student);
		return new ResponseEntity<Student>(student, HttpStatus.OK);
    }
}