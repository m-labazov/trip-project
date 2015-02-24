package ua.home.trip.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ua.home.trip.data.Trip;
import ua.home.trip.enums.ERegex;

public class TripValidator {

	public static Map<String, String> validate(Trip trip) {
		Map<String, String> errors = new HashMap<>();
		Matcher matcher = Pattern.compile(ERegex.ENG_TEXT.getExpr()).matcher(trip.getName());
		if (!matcher.matches()) {
			errors.put("name", ERegex.ENG_TEXT.getMessageText());
		}
		if (trip.getStartDate() == null) {
			errors.put("startDate", "'Start date' can't be empty.");
		}
		if (trip.getEndDate() == null) {
			errors.put("endDate", "'End date' can't be empty.");
		}
		if (!errors.containsKey("startDate") && !errors.containsKey("endDate")
				&& trip.getStartDate().getTime() > trip.getEndDate().getTime()) {
			errors.put("startDate", "'Start date' must be before 'End date'.");
		}
		return errors;
	}
}
