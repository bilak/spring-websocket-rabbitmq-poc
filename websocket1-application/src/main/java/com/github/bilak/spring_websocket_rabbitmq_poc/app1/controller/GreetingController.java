package com.github.bilak.spring_websocket_rabbitmq_poc.app1.controller;

import com.github.bilak.spring_websocket_rabbitmq_poc.app1.domain.Greeting;
import com.github.bilak.spring_websocket_rabbitmq_poc.app1.domain.HelloMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by lvasek on 23/06/16.
 */
@Controller
public class GreetingController {

	private static final Logger logger = LoggerFactory.getLogger(GreetingController.class);

	@Autowired
	private SimpMessagingTemplate template;

	@MessageMapping("/application1")
	@SendTo("/topic/application1")
	public Greeting greeting(HelloMessage message) throws Exception {
		logger.debug("Received message {}", message);
		sendSomethingBetween("I guess Application1 will say hello to " + message.getName());
		Thread.sleep(3000); // simulated delay
		return new Greeting("Application1: hello " + message.getName() + "!");
	}

	private void sendSomethingBetween(String something) {
		template.convertAndSend("/topic/application1", new Greeting(something));
	}
}
