package ua.home.trip.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseController {

    @Autowired
	protected ObjectMapper mapper;

    protected JsonNode createSuccessResponse() {
		return createSuccessResponse(null);
	}

    protected JsonNode createSuccessResponse(Object body) {
		Response response = new Response();
		response.setStatusCode("OK");
		response.setData(body);
        return mapper.valueToTree(response);
	}

	protected JsonNode createFailResponse() {
		return createFailResponse(null);
	}

	protected JsonNode createFailResponse(Map<String, String> errors) {
		return createFailResponse(errors, HttpStatus.BAD_REQUEST);
	}

	protected JsonNode createFailResponse(Map<String, String> errors, HttpStatus statusCode) {
		Response response = new Response();
        response.setStatusCode("FAIL");
		response.setData(errors);
		response.setHttpStatus(statusCode.toString());
        return mapper.valueToTree(response);
	}
}
