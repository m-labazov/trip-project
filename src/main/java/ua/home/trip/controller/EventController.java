package ua.home.trip.controller;

import com.fasterxml.jackson.databind.JsonNode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.home.trip.api.service.IEventService;
import ua.home.trip.data.Event;

import java.util.List;

@Controller
public class EventController extends BaseController {

    @Autowired
    private IEventService eventService;

    @RequestMapping(value = "action/event/{tripId}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonNode saveLink(@RequestBody Event event, @PathVariable("tripId") String tripId) {
        event.setTripId(tripId);
        eventService.insert(event);
        return createSuccessResponse();
    }

    @RequestMapping(value = "action/event/{tripId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonNode loadEvents(@PathVariable("tripId") String tripId) {
        List<Event> events = eventService.findEvents(tripId);
        return createSuccessResponse(events);
    }

}
