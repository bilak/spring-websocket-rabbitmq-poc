package com.github.bilak.spring_websocket_rabbitmq_poc.app2.domain;

/**
 * Created by lvasek on 23/06/16.
 */
public class Greeting {

	private String content;

	public Greeting(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

}