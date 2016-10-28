package com.github.bilak.poc.hystrix_oauth2_feign.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by lvasek on 09/09/16.
 */
@SpringBootApplication
@RestController
public class AuthorizationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationServiceApplication.class, args);
	}

	@GetMapping({ "/user", "me" })
	public Map<String, String> userInfo(Principal principal) {
		Map<String, String> userInfo = new LinkedHashMap<>();
		if (principal != null) {
			if (!StringUtils.isEmpty(principal.getName()))
				userInfo.put("name", principal.getName());
		}
		return userInfo;
	}
}
