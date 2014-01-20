package ua.home.trip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.home.trip.api.service.ILocationService;
import ua.home.trip.controller.Locations.Location;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class MarkerController {

	@Autowired
	private ILocationService locationService;
	@Autowired
	private ObjectMapper mapper;

	@RequestMapping(method = RequestMethod.POST, value = "/action/marker/store")
	@ResponseBody
	public String storeMarkers(@RequestBody Locations locations) {
		locationService.insert(locations);
		for (Location marker : locations.getLocations()) {
			System.out.println(marker.getTitle() + ": " + marker.getOb() + ":"
					+ marker.getNb());
		}

		return "Ok";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/action/marker/get/{id}")
	@ResponseBody
	public String getMarkers(@RequestParam(required = false) String id)
			throws JsonProcessingException {
		Locations result = locationService.get(id);
		if (result == null) {
			return "";
		}
		return mapper.writeValueAsString(result);
	}

}
