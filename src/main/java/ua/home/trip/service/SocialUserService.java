package ua.home.trip.service;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import javax.annotation.Resource;

public class SocialUserService implements SocialUserDetailsService {

    @Resource
    private UserDetailsService userService;

    public SocialUserService(UserDetailsService userService) {
        this.userService = userService;
    }

    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
        return (SocialUserDetails) userService.loadUserByUsername(userId);
    }

}
