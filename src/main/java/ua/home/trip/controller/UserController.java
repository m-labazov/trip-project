package ua.home.trip.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.home.trip.api.data.IUser;
import ua.home.trip.api.service.ITripService;
import ua.home.trip.api.service.IUserService;

import com.fasterxml.jackson.databind.JsonNode;

@Controller
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;
	@Autowired
	private ITripService tripService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

	@RequestMapping(value = "loadNewMembersList/{tripId}", method = RequestMethod.GET)
	@ResponseBody
	public JsonNode loadNewMembersList(@PathVariable String tripId) {
		List<IUser> contactList = userService.loadContactList();
		List<IUser> newMembersList = tripService.loadNewMembers(tripId, contactList);
		return createSuccessResponse(newMembersList);
	}

	@RequestMapping(value = "user/profileImage", method = RequestMethod.GET)
	public void getUserProfileImage(HttpServletResponse response) {
		try {
			IUser user = (IUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			response.getOutputStream().write(userService.getUserProfileImage(user.getId()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
