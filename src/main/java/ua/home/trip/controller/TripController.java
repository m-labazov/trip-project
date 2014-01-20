package ua.home.trip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.home.trip.api.service.ITripService;
import ua.home.trip.data.Trip;

@Controller
public class TripController extends BaseController {

	@Autowired
	private ITripService tripService;

	@RequestMapping(value = "action/trip", method = RequestMethod.POST)
	@ResponseBody
	public String saveTrip(@RequestBody Trip trip) {
		tripService.insert(trip);
		return createSuccessResponse(null);
	}

	@RequestMapping(value = "action/trip", method = RequestMethod.GET)
	@ResponseBody
	public String loadTrip(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "tripName", required = false) String tripName) {
		Trip trip = tripService.findTrip(tripName);
		return createSuccessResponse(trip);
	}

}
