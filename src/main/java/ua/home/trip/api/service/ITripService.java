package ua.home.trip.api.service;

import java.util.List;

import ua.home.trip.api.data.IUser;
import ua.home.trip.data.Trip;


public interface ITripService extends ILinkService, IAbstractService<Trip> {

    List<Trip> findList();

    List<String> loadTripMemberNames(String id);

	void addTripMember(String id, String userId);

	List<IUser> loadNewMembers(String tripId, List<IUser> contactList);

}
