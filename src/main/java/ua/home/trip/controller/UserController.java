package ua.home.trip.controller;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public void getUserProfileImage(@RequestParam(value = "userId", required = false) String userId,
			HttpServletResponse response) {
		try {
			if (StringUtils.isBlank(userId)) {
				IUser user = (IUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				userId = user.getId();
			}
			response.getOutputStream().write(userService.getUserProfileImage(userId));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		List<Integer> ints = new ArrayList<Integer>();
		for (int i = 0; i < 10000000; i++) {
			int value = new Random().nextInt();
			ints.add(value);
		}

		System.out.println("Writes to list");
		long startTimeList = System.currentTimeMillis();
		List<Integer> resultList = new ArrayList<Integer>();
		for (Integer i : ints) {
			resultList.add(i);
		}
		long endTimeList = System.currentTimeMillis();

		System.out.println("Writes to file");
		RandomAccessFile file = new RandomAccessFile(new File("temp.txt"), "rw");
		long startTimeFile = System.currentTimeMillis();
		for (Integer i : ints) {
			file.writeInt(i);
		}
		long endTimeFile = System.currentTimeMillis();

		System.out.println("List write time: " + (endTimeList - startTimeList));
		System.out.println("File write time: " + (endTimeFile - startTimeFile));
		System.out.println();

		System.out.println("Read test");

		long readListStartTime = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			Integer integer = resultList.get(new Random().nextInt(10000000));
		}
		long readListEndTime = System.currentTimeMillis();

		long readFileStartTime = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			file.seek(new Random().nextInt(10000000));
			Integer integer = file.readInt();
		}
		long readFileEndTime = System.currentTimeMillis();

		System.out.println("List read time: " + (readListEndTime - readListStartTime));
		System.out.println("File read time: " + (readFileEndTime - readFileStartTime));
	}

}
