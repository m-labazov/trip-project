package ua.home.trip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.home.trip.api.data.IUser;
import ua.home.trip.api.service.IUserRepository;
import ua.home.trip.api.service.IUserService;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        IUser user = repository.loadUserByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + userName);
        }
        return user;
    }

}
