package ua.home.trip.api.service;

import java.util.List;

import ua.home.trip.api.data.IUser;

public interface IUserRepository {

    IUser loadUserByUsername(String userName);

    IUser loadUserById(String userId);

	List<IUser> loadContactList(String userId);

	List<IUser> loadUsersByIds(List<String> members);

	IUser loadUserBySocialId(String providerId, String userId);

}
