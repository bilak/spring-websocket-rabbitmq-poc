package com.github.bilak.spring_websocket_rabbitmq_poc.app2.configuration;

import com.github.bilak.spring_websocket_rabbitmq_poc.app2.security.oauth2.PocPrincipalExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * Created by lvasek on 28/10/2016.
 */
@Configuration
public class SecurityConfiguration {

	@Configuration
	@EnableResourceServer
	public static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

		@Autowired
		private ResourceServerProperties ssoProperties;

		@Bean
		UserInfoTokenServices defaultUserInfoTokenServices() {
			UserInfoTokenServices userInfoTokenServices = new UserInfoTokenServices(
					ssoProperties.getUserInfoUri(),
					ssoProperties.getClientId()
			);
			userInfoTokenServices.setPrincipalExtractor(pocPrincipalExtractor());
			return userInfoTokenServices;
		}

		@Bean
		PrincipalExtractor pocPrincipalExtractor() {
			return new PocPrincipalExtractor();
		}

		@Override
		public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
			resources.tokenServices(defaultUserInfoTokenServices());
		}

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().anyRequest().authenticated();
		}
	}

}
