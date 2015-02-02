package ua.home.trip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.home.trip.api.service.IEventService;
import ua.home.trip.api.service.ITripService;
import ua.home.trip.data.Event;

import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class EventController extends BaseController {

    @Autowired
    private IEventService eventService;
	@Autowired
	private ITripService tripService;

	@RequestMapping(value = "event/{tripId}", method = RequestMethod.PUT)
    @ResponseBody
	public JsonNode saveLink(@RequestBody Event event, @PathVariable("tripId") String tripId) {
        event.setTripId(tripId);
        eventService.insert(event);
        return createSuccessResponse();
    }

	@RequestMapping(value = "event/{tripId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonNode loadEvents(@PathVariable("tripId") String tripId) {
        List<Event> events = eventService.findEvents(tripId);
        return createSuccessResponse(events);
    }

}
