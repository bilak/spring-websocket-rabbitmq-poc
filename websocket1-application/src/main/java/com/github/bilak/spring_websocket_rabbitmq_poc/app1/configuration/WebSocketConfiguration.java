package com.github.bilak.spring_websocket_rabbitmq_poc.app1.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * Created by lvasek on 23/06/16.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration {

	@Configuration
	public static class WebSocketMessageBrokerConfiguration extends AbstractWebSocketMessageBrokerConfigurer {

		@Override
		public void configureMessageBroker(MessageBrokerRegistry config) {
			config.setApplicationDestinationPrefixes("/app");
			config.enableStompBrokerRelay("/topic")
					.setAutoStartup(true)
					.setClientLogin("cloud")
					.setClientPasscode("cloud")
					.setSystemLogin("cloud")
					.setSystemPasscode("cloud")
					.setRelayHost("localhost")
					.setSystemHeartbeatReceiveInterval(24000)
					.setSystemHeartbeatSendInterval(24000)
					.setRelayPort(61613)
					.setVirtualHost("/cloud");
		}

		@Override
		public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
			stompEndpointRegistry.addEndpoint("/application1")
					.setHandshakeHandler(new DefaultHandshakeHandler())
					.withSockJS()
					.setInterceptors(new HttpSessionHandshakeInterceptor());
		}
	}

}
