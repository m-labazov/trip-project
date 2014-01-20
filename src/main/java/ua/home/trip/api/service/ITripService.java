package ua.home.trip.api.service;

import ua.home.trip.data.Link;
import ua.home.trip.data.Trip;

public interface ITripService {

	void addLink(String tripId, Link link);

	Trip findTrip(String tripName);

	void insert(Trip trip);

	void deleteLink(String tripId, String linkName);

	void updateLink(String tripId, Link link);

}
