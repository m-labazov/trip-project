package ua.home.trip.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ua.home.trip.data.Link;

public class LinkValidator {

	public static Map<String, String> validate(Link link, String tripId) {
		Map<String, String> errors = new HashMap<>();
		if (StringUtils.isEmpty(link.getName())) {
			errors.put("name", "'Name' can't be empty.");
		}
		if (link.getType() == null) {
			errors.put("type", "'Type' can't be empty.");
		}
		if (StringUtils.isBlank(tripId)) {
			errors.put("tripId", "'tripId' can't be empty.");
		}
		return errors;
	}

}
