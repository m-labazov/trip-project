package ua.home.trip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UsersConnectionRepository connectionRepository;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public ModelAndView signUp() {
        ModelAndView result = new ModelAndView("login");
        return result;
    }

}
