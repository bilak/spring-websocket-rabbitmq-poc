package com.github.bilak.spring_websocket_rabbitmq_poc.app2.security.oauth2;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by lvasek on 28/10/2016.
 */
public class PocUser implements Serializable {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return "PocUser{" +
				"name='" + name + '\'' +
				'}';
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final PocUser other = (PocUser) obj;
		return Objects.equals(this.name, other.name);
	}
}
