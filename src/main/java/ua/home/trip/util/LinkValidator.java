package ua.home.trip.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import ua.home.trip.data.Link;
import ua.home.trip.enums.ERegex;

public class LinkValidator {

	public static Map<String, String> validate(Link link, String tripId) {
		Map<String, String> errors = new HashMap<>();

		Matcher matcher = Pattern.compile(ERegex.ENG_TEXT.getExpr()).matcher(link.getName());
		if (!matcher.matches()) {
			errors.put("name", ERegex.ENG_TEXT.getMessageText());
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
