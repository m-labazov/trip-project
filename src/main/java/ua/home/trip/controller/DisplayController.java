package ua.home.trip.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DisplayController extends BaseController {

	@RequestMapping(method = RequestMethod.GET, value = "trip")
	public ModelAndView displayTrip(@RequestParam(value = "id", required = false) String id) {
		ModelAndView modelAndView = createDefaultModelAndView();
		if (StringUtils.isBlank(id)) {
			return new ModelAndView("errorPage");
		}
		modelAndView.addObject("id", id);
		modelAndView.addObject("tab", "trip");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "display")
	public ModelAndView displayTab(@RequestParam(value = "tab", required = true) String tab) {
		if (StringUtils.isBlank(tab)) {
			return new ModelAndView("errorPage");
		}
		return new ModelAndView(tab);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView displayMain() {
		ModelAndView modelAndView = createDefaultModelAndView();
		modelAndView.addObject("tab", "tripList");
		return modelAndView;
	}

	private ModelAndView createDefaultModelAndView() {
		return new ModelAndView("index");
	}

}
