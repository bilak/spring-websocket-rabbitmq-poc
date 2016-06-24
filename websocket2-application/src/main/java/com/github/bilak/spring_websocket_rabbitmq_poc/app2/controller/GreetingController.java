package com.github.bilak.spring_websocket_rabbitmq_poc.app2.controller;

import com.github.bilak.spring_websocket_rabbitmq_poc.app2.domain.Greeting;
import com.github.bilak.spring_websocket_rabbitmq_poc.app2.domain.HelloMessage;
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

	@Autowired
	private SimpMessagingTemplate template;

	@MessageMapping("/application2")
	@SendTo("/topic/application2")
	public Greeting greeting(HelloMessage message) throws Exception {
		sendSomethingBetween("I guess Application2 will say hello to " + message.getName());
		Thread.sleep(3000); // simulated delay
		return new Greeting("Application2: hello " + message.getName() + "!");
	}

	private void sendSomethingBetween(String something) {
		template.convertAndSend("/topic/application2", new Greeting(something));
	}
}
