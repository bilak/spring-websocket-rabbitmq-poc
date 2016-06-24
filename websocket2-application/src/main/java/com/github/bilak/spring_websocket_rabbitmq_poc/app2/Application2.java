package com.github.bilak.spring_websocket_rabbitmq_poc.app2;

import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Created by lvasek on 23/06/16.
 */
@SpringBootApplication
public class Application2 {

	public static void main(String[] args) {
		SpringApplication.run(Application2.class, args);
	}

	@Bean
	RequestDumperFilter requestDumperFilter() {
		return new RequestDumperFilter();
	}
}
