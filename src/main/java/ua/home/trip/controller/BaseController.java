package ua.home.trip.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;

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
        Response response = new Response();
        response.setStatusCode("FAIL");
        return mapper.valueToTree(response);
    }
}
