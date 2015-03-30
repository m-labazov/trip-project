package ua.home.trip.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.home.trip.api.data.ITrip;
import ua.home.trip.api.data.IUser;
import ua.home.trip.api.service.ITripService;
import ua.home.trip.data.Trip;
import ua.home.trip.util.TripValidator;

import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class TripController extends BaseController {

	@Autowired
	private ITripService tripService;

	@RequestMapping(value = "trip", method = RequestMethod.POST)
	@ResponseBody
	public JsonNode saveTrip(@RequestBody Trip trip) {
		Map<String, String> errors = TripValidator.validate(trip);
		if (errors.isEmpty()) {
			tripService.insert(trip);
			return createSuccessResponse();
		} else {
			return createFailResponse(errors);
		}
	}

	@RequestMapping(value = "trip/list", method = RequestMethod.GET)
	@ResponseBody
	public JsonNode loadTripList() {
		IUser user = (IUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<? extends ITrip> tripList = tripService.findList(user.getId());
		return createSuccessResponse(tripList);
	}

	@RequestMapping(value = "trip/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JsonNode loadTrip(@PathVariable String id) {
		ITrip trip = tripService.loadById(id);
		return createSuccessResponse(trip);
	}

	@RequestMapping(value = "trip/{id}", method = RequestMethod.DELETE)
	@ResponseBody
    public JsonNode deleteTrip(@PathVariable String id) {
        tripService.delete(id);
		return createSuccessResponse();
	}

	@RequestMapping(value = "trip", method = RequestMethod.PUT)
	@ResponseBody
	public JsonNode updateTrip(@RequestBody Trip trip) {
		Map<String, String> errors = TripValidator.validate(trip);
		if (errors.isEmpty()) {
			tripService.update(trip);
			return createSuccessResponse();
		} else {
			return createFailResponse(errors);
		}
	}

	@RequestMapping(value = "trip/{id}/members", method = RequestMethod.GET)
    @ResponseBody
    public JsonNode loadTripMembers(@PathVariable String id) {
		List<IUser> users = tripService.loadTripMembers(id);
		return createSuccessResponse(users);
    }

	@RequestMapping(value = "trip/{id}/member/{userId}", method = RequestMethod.PUT)
	@ResponseBody
	public JsonNode addTripMember(@PathVariable String id, @PathVariable String userId) {
		tripService.addTripMember(id, userId);
		return createSuccessResponse();
	}

	@RequestMapping(value = "trip/{id}/member/{userId}", method = RequestMethod.DELETE)
	@ResponseBody
	public JsonNode expelTripMember(@PathVariable String id, @PathVariable String userId) {
		tripService.expelTripMember(id, userId);
		return createSuccessResponse();
	}
}
