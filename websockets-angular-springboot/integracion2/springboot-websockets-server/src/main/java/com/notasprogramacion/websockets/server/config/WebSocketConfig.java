package com.notasprogramacion.websockets.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebSocketMessageBroker
@ComponentScan(basePackages="com.notasprogramacion.websockets.server")
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	@Autowired
	ObjectMapper objectMapper;
	
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
    	config.enableSimpleBroker("/topic/", "/queue/");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/websocket-server-app")
        .setAllowedOrigins("*")
        .addInterceptors(new HttpHandshakeInterceptor());
    }
    
}