package ua.home.trip.api.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import ua.home.trip.api.data.IUser;


public interface IUserService extends UserDetailsService {

	List<IUser> loadContactList();

	List<IUser> loadUsersByIds(List<String> members);

	IUser loadUserBySocialId(String providerId, String providerUserId);

	byte[] getUserProfileImage(String userId);

}
