package ua.home.trip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.home.trip.api.service.ILinkService;
import ua.home.trip.data.Link;
import ua.home.trip.data.Marker;
import ua.home.trip.data.filter.Filter;
import ua.home.trip.enums.ELinkType;
import ua.home.trip.util.LinkValidator;

import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class LinkController extends BaseController {

	@Autowired
    private ILinkService linkService;

	@RequestMapping(value = "link/{tripId}", method = RequestMethod.PUT)
	@ResponseBody
    public JsonNode saveLink(@RequestBody Link link,
			@PathVariable("tripId") String tripId) {
		Map<String, String> errors = LinkValidator.validate(link, tripId);
		if (!errors.isEmpty()) {
			return createFailResponse(errors);
		}
		if (StringUtils.isBlank(link.getLinkId())) {
            linkService.addLink(tripId, link);
		} else {
            linkService.updateLink(tripId, link);
		}
		return createSuccessResponse(link);
	}

	@RequestMapping(value = "link/types", method = RequestMethod.GET)
	@ResponseBody
    public JsonNode getLinkType() {
		Map<String, String> values = new HashMap<>();
		for (ELinkType type : ELinkType.values()) {
			values.put(type.name(), type.getTitle());
		}
		return createSuccessResponse(values);
	}

	@RequestMapping(value = "link/{tripId}/{linkId}", method = RequestMethod.DELETE)
	@ResponseBody
	public JsonNode deleteLink(@PathVariable("tripId") String tripId, @PathVariable("linkId") String linkName) {
        linkService.deleteLink(tripId, linkName);
		return createSuccessResponse();
	}

	@RequestMapping(value = "marker/{tripId}/{linkId}", method = RequestMethod.PUT)
	@ResponseBody
    public JsonNode addMarker(@PathVariable("tripId") String tripId,
			@PathVariable("linkId") String linkId, @RequestBody Marker marker) {
        linkService.addMarkerToLink(tripId, linkId, marker);
		return createSuccessResponse();
	}

	@RequestMapping(value = "marker/{tripId}/{linkId}", method = RequestMethod.DELETE)
	@ResponseBody
    public JsonNode removeMarker(@PathVariable("tripId") String tripId,
			@PathVariable("linkId") String linkId) {
        linkService.addMarkerToLink(tripId, linkId, null);
		return createSuccessResponse("Deleted");
	}

	@RequestMapping(value = "link/{tripId}", method = RequestMethod.GET)
    @ResponseBody
    public JsonNode loadTrip(@PathVariable(value = "tripId") String id,
            @RequestParam(value = "type", required = false) String type) {
        Filter filter = new Filter();
        filter.setId(id);
        filter.setType(type);
        List<Link> links = linkService.findLinks(filter);
        return createSuccessResponse(links);
    }

}
