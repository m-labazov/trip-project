package ua.home.trip.api.service;

import ua.home.trip.api.data.IUser;

public interface IUserRepository {

    IUser loadUserByUsername(String userName);

    IUser loadUserById(String userId);

}
