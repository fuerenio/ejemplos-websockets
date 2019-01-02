package com.notasprogramacion.back.rest.config;

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
@ComponentScan(basePackages="com.notasprogramacion.back")
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
        registry.addEndpoint("/notaspro-app")
        .setAllowedOrigins("*")
        .addInterceptors(new HttpHandshakeInterceptor());
    }
    /*
    @Override 
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) { 
        // Workaround for issue 2445: https://github.com/spring-projects/spring-boot/issues/2445 
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver(); 
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON); 
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter(); 
        converter.setObjectMapper(objectMapper); 
        converter.setContentTypeResolver(resolver); 
        messageConverters.add(converter); 
        return false; 
    } */
}