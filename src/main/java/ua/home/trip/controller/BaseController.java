package ua.home.trip.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseController {

	@Autowired
	protected ObjectMapper mapper;

	protected String createSuccessResponse(Object body) {
		Response response = new Response();
		response.setStatusCode("Ok");
		response.setBody(body);
		try {
			return mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			// TODO logger
			throw new RuntimeException();
		}
	}

}
