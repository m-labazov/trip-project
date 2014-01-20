package ua.home.trip.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.home.trip.api.service.ITripService;
import ua.home.trip.data.Link;
import ua.home.trip.enums.ELinkType;

@Controller
public class LinkController extends BaseController {

	@Autowired
	private ITripService tripService;

	@RequestMapping(value = "action/link/{tripId}", method = RequestMethod.PUT)
	@ResponseBody
	public String saveLink(@RequestBody Link link,
			@PathVariable("tripId") String tripId) {
		if (link.getName() == null) {
			return "false";
		}
		if (link.getUrl() == null) {
			return "false";
		}

		if (StringUtils.isBlank(link.getLinkId())) {
			tripService.addLink(tripId, link);
		} else {
			tripService.updateLink(tripId, link);
		}
		return createSuccessResponse(null);
	}

	@RequestMapping(value = "action/link/types", method = RequestMethod.GET)
	@ResponseBody
	public String getLinkType() {
		Map<String, String> values = new HashMap<>();
		for (ELinkType type : ELinkType.values()) {
			values.put(type.name(), type.getTitle());
		}
		return createSuccessResponse(values);
	}

	@RequestMapping(value = "action/link/{tripId}/{linkName}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteLink(@PathVariable("tripId") String tripId,
			@PathVariable("linkName") String linkName) {
		tripService.deleteLink(tripId, linkName);
		return createSuccessResponse(null);
	}

}
