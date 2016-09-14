package com.github.bilak.spring_websocket_rabbitmq_poc.app2.configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.*;

/**
 * Created by lvasek on 14/09/16.
 */
public class WebSocketHeaderFilter implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		WebSocketHeaderFilterWrapper wrapper = new WebSocketHeaderFilterWrapper(request);
		Optional.ofNullable(wrapper.getHeader("upgrade"))
				.ifPresent(a -> {
							if (a.equalsIgnoreCase("websocket")) {
								wrapper.addHeader("connection", "Upgrade");
							}
						}
				);
		filterChain.doFilter(wrapper, servletResponse);
	}

	@Override
	public void destroy() {

	}

	private static class WebSocketHeaderFilterWrapper extends HttpServletRequestWrapper {

		public WebSocketHeaderFilterWrapper(HttpServletRequest request) {
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
			/*
			Optional<Boolean> isPresent = headerMap.keySet()
					.stream()
					.map(a -> a.equalsIgnoreCase(name))
					.findFirst();

			if (!isPresent.isPresent()) {
				values.add(headerMap.get(name));
			}
			 */

			return Collections.enumeration(values);
		}

	}
}
