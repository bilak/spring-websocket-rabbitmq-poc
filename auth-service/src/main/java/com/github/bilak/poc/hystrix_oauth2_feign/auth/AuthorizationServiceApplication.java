package com.github.bilak.poc.hystrix_oauth2_feign.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by lvasek on 09/09/16.
 */
@SpringBootApplication
@RestController
public class AuthorizationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServiceApplication.class, args);
	}

	@GetMapping("/user")
	public Principal userInfo(Principal principal) {
		return principal;
	}
}
