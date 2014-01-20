package ua.home.trip.api.repository;

import ua.home.trip.data.Link;
import ua.home.trip.data.Trip;

public interface ITripRepository {

	void addLink(String tripId, Link link);

	Trip getTrip(String tripName);

	void insert(Trip trip);

	void deleteLink(String tripId, String linkName);

	void updateLink(String tripId, Link link);

}
