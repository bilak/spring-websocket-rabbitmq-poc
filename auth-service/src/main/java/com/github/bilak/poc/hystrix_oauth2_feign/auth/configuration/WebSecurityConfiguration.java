package com.github.bilak.poc.hystrix_oauth2_feign.auth.configuration;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by lvasek on 09/09/16.
 */
@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.formLogin().loginPage("/login").permitAll()
				.and()
				.requestMatchers()
				.antMatchers("/", "/login", "/oauth/authorize", "/oauth/confirm_access")
				.and()
				.authorizeRequests()
				.anyRequest().authenticated();
	}
}
