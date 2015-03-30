package ua.home.trip.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.home.trip.api.data.IInformation;
import ua.home.trip.api.service.IInformationService;
import ua.home.trip.data.Information;

import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class InformationController extends BaseController {

	@Resource
	private IInformationService service;

	@RequestMapping(value = "information", method = RequestMethod.PUT)
	@ResponseBody
	public JsonNode createInformation(@RequestBody Information information) {
		if (StringUtils.isEmpty(information.getId())) {
			service.insert(information);
		} else {
			service.update(information);
		}
		return createSuccessResponse();
	}

	@RequestMapping(value = "information/list/{locationId}", method = RequestMethod.GET)
	@ResponseBody
	public JsonNode loadInformationList(@PathVariable(value = "locationId") String locationId) {
		List<? extends IInformation> result = service.findList(locationId);
		return createSuccessResponse(result);
	}

	@RequestMapping(value = "information/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JsonNode loadInformation(@PathVariable String id) {
		IInformation information = service.loadById(id);
		return createSuccessResponse(information);
	}

	@RequestMapping(value = "information/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public JsonNode deleteInformation(@PathVariable String id) {
		service.delete(id);
		return createSuccessResponse();
	}

}
