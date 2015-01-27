package ua.home.trip.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import ua.home.trip.data.Trip;

public class TripValidator {

	public static Map<String, String> validate(Trip trip) {
		Map<String, String> errors = new HashMap<>();
		if (StringUtils.isEmpty(trip.getName())) {
			errors.put("name", "'Name' can't be empty.");
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
