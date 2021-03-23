package com.deybson.osworks.api.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class CreateResourceEvent extends ApplicationEvent{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	private String path;
	private Long id;
	
	
	
	public CreateResourceEvent(Object source, HttpServletResponse response, String path, Long id) {
		super(source);
		this.response = response;
		this.path = path;
		this.id = id;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public String getPath() {
		return path;
	}
	public Long getId() {
		return id;
	}
	
	
}
