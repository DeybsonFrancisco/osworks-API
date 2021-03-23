package com.deybson.osworks.api.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.deybson.osworks.api.event.CreateResourceEvent;

@Component
public class CreateResourceListerner implements ApplicationListener<CreateResourceEvent>{

	@Override
	public void onApplicationEvent(CreateResourceEvent event) {
		
		HttpServletResponse response = event.getResponse();
		String path = event.getPath();
		Long id = event.getId();
		
		addHeaderLocation(response, path, id);
	}

	private void addHeaderLocation(HttpServletResponse response, String path, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path(String.format("/{%s}", path))
				.buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
		
		
	}

}
