package com.github.bilak.spring_websocket_rabbitmq_poc.app1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

/**
 * Created by lvasek on 15/09/16.
 */
@Configuration
@EnableSpringHttpSession
public class SpringSessionConfiguration {

	@Bean
	SessionRepository sessionRepository(){
		return new MapSessionRepository();
	}


}
