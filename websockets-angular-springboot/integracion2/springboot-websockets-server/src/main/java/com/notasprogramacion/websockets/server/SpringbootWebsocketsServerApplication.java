package com.notasprogramacion.websockets.server;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootWebsocketsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebsocketsServerApplication.class, args);
	}
	
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

