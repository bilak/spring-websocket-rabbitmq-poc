package com.github.bilak.spring_websocket_rabbitmq_poc.gateway;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by lvasek on 15/09/16.
 */
@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/uaa/**", "/login", "/resources/**", "/css/**", "/app1/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.csrf().disable();

	}
}
