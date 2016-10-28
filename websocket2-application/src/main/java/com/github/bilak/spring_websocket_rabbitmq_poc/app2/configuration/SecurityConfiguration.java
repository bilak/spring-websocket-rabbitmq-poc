package com.github.bilak.spring_websocket_rabbitmq_poc.app2.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by lvasek on 28/10/2016.
 */
@Configuration
public class SecurityConfiguration {

	@Configuration
	@EnableResourceServer
	public static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().anyRequest().authenticated();
		}
	}

}
