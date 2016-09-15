package com.github.bilak.spring_websocket_rabbitmq_poc.gateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * Created by lvasek on 24/06/16.
 */
@SpringBootApplication
@EnableZuulProxy
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	RequestDumperFilter requestDumperFilter() {
		return new RequestDumperFilter();
	}

	@Component
	public static class WebSocketHeaderFilter extends ZuulFilter {

		@Override
		public String filterType() {
			return "pre";
		}

		@Override
		public int filterOrder() {
			return 0;
		}

		@Override
		public boolean shouldFilter() {
			return true;
		}

		@Override
		public Object run() {
			RequestContext ctx = RequestContext.getCurrentContext();
			RequestWrapper wrapper = new RequestWrapper(ctx.getRequest());
			String upgradeHeader = wrapper.getHeader("Upgrade");
			if (null == upgradeHeader) {
				upgradeHeader = wrapper.getHeader("upgrade");
			}
			if (null != upgradeHeader && "websocket".equalsIgnoreCase(upgradeHeader)) {
				wrapper.addHeader("connection", "Upgrade");
				ctx.addZuulRequestHeader("connection", "Upgrade");
				ctx.setRequest(wrapper);
			}
			return null;
		}
	}

	private static class RequestWrapper extends HttpServletRequestWrapper {

		public RequestWrapper(HttpServletRequest request) {
			super(request);
		}

		private Map<String, String> headerMap = new HashMap<String, String>();

		/**
		 * add a header with given name and value
		 *
		 * @param name
		 * @param value
		 */
		public void addHeader(String name, String value) {
			headerMap.put(name, value);
		}

		@Override
		public String getHeader(String name) {
			String headerValue = super.getHeader(name);
			if (headerMap.containsKey(name)) {
				headerValue = headerMap.get(name);
			}
			return headerValue;
		}

		/**
		 * get the Header names
		 */
		@Override
		public Enumeration<String> getHeaderNames() {
			List<String> names = Collections.list(super.getHeaderNames());
			for (String name : headerMap.keySet()) {
				names.add(name);
			}
			return Collections.enumeration(names);
		}

		@Override
		public Enumeration<String> getHeaders(String name) {
			List<String> values = Collections.list(super.getHeaders(name));

			if (headerMap.containsKey(name)) {
				values.add(headerMap.get(name));
			}

			return Collections.enumeration(values);
		}

	}

}
