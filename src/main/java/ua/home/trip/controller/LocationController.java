package ua.home.trip.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.home.trip.api.data.ILocation;
import ua.home.trip.api.service.ILocationService;
import ua.home.trip.data.Location;

import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class LocationController extends BaseController {

	@Autowired
	private ILocationService service;

	@RequestMapping(value = "location", method = RequestMethod.PUT)
	@ResponseBody
	public JsonNode createLocation(@RequestBody Location location) {
		if (StringUtils.isEmpty(location.getId())) {
			service.insert(location);
		} else {
			service.update(location);
		}
		return createSuccessResponse();
	}

	@RequestMapping(value = "location/list/{tripId}", method = RequestMethod.GET)
	@ResponseBody
	public JsonNode loadLocationList(@PathVariable(value = "tripId") String tripId) {
		List<? extends ILocation> result = service.findList(tripId);
		return createSuccessResponse(result);
	}

	@RequestMapping(value = "location/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JsonNode loadLocation(@PathVariable String id) {
		ILocation location = service.loadById(id);
		return createSuccessResponse(location);
	}

	@RequestMapping(value = "location/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public JsonNode deleteLocation(@PathVariable String id) {
		service.delete(id);
		return createSuccessResponse();
	}

}
