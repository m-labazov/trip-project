package ua.home.trip.controller;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.home.trip.api.service.ITripService;
import ua.home.trip.data.Link;
import ua.home.trip.data.Trip;
import ua.home.trip.data.filter.Filter;

import java.util.List;

@Controller
public class TripController extends BaseController {

	@Autowired
	private ITripService tripService;

	@RequestMapping(value = "action/trip", method = RequestMethod.POST)
	@ResponseBody
    public JsonNode saveTrip(@RequestBody Trip trip) {
		tripService.insert(trip);
		return createSuccessResponse();
	}

	@RequestMapping(value = "action/trip/{id}", method = RequestMethod.GET)
	@ResponseBody
    public JsonNode loadTrip(@PathVariable(value = "id") String id,
			@RequestParam(value = "type", required = false) String type) {
		Filter filter = new Filter();
		filter.setId(id);
		filter.setType(type);
		List<Link> links = tripService.findLinks(filter);
		return createSuccessResponse(links);
	}

	@RequestMapping(value = "action/trip/list", method = RequestMethod.GET)
	@ResponseBody
    public JsonNode loadTrip() {
		List<Trip> tripList = tripService.findTripList();
		return createSuccessResponse(tripList);
	}

	@RequestMapping(value = "action/trip/{id}", method = RequestMethod.DELETE)
	@ResponseBody
    public JsonNode deleteTrip(@PathVariable String id) {
		tripService.deleteTrip(id);
		return createSuccessResponse();
	}

	@RequestMapping(value = "action/trip", method = RequestMethod.PUT)
	@ResponseBody
    public JsonNode updateTrip(@RequestBody Trip trip) {
		tripService.updateTrip(trip);
		return createSuccessResponse();
	}
}
