package ua.home.trip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MarkerController {

	@RequestMapping(method = RequestMethod.POST, value = "/action/marker/store")
	@ResponseBody
	public String storeMarkers(@RequestBody Locations locations) {

		// for (Marker marker : markers) {
		// System.out.println(marker.getTitle() + ": " + marker.getX() + ":"
		// + marker.getY());
		// }

		return "Ok";
	}

}
