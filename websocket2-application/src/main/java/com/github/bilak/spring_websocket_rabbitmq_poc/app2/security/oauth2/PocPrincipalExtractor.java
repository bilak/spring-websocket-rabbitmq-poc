package com.github.bilak.spring_websocket_rabbitmq_poc.app2.security.oauth2;

import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;

import java.util.Map;

/**
 * Created by lvasek on 28/10/2016.
 */
public class PocPrincipalExtractor implements PrincipalExtractor {
	@Override
	public Object extractPrincipal(Map<String, Object> map) {
		if (map.containsKey("name")) {
			PocUser poc = new PocUser();
			poc.setName((String) map.get("name"));
			return poc;
		}

		return null;
	}
}
