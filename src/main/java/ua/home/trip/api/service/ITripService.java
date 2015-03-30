package ua.home.trip.api.service;

import java.util.List;

import ua.home.trip.api.data.ITrip;
import ua.home.trip.api.data.IUser;


public interface ITripService extends ILinkService, IAbstractService<ITrip> {

	List<IUser> loadTripMembers(String id);

	void addTripMember(String id, String userId);

	List<IUser> loadNewMembers(String tripId, List<IUser> contactList);

	void expelTripMember(String id, String userId);

}
