package com.github.bilak.spring_websocket_rabbitmq_poc.app1.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by lvasek on 23/06/16.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		//config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app");
		config.enableStompBrokerRelay("/topic")
				.setAutoStartup(true)
				.setClientLogin("guest")
				.setClientPasscode("guest")
				.setSystemLogin("guest")
				.setSystemPasscode("guest")
				.setRelayHost("localhost")
				.setRelayPort(61613);
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
		stompEndpointRegistry.addEndpoint("/application1").withSockJS();
	}
}
