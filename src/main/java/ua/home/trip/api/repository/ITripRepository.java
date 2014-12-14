package ua.home.trip.api.repository;

import java.util.List;

import ua.home.trip.data.Link;
import ua.home.trip.data.Marker;
import ua.home.trip.data.Trip;
import ua.home.trip.data.filter.Filter;

public interface ITripRepository {

	void addLink(String tripId, Link link);

	List<Link> getLinks(Filter filter);

	void insert(Trip trip);

	void deleteLink(String tripId, String linkName);

	void updateLink(String tripId, Link link);

	void addMarkerToLink(String tripId, String linkId, Marker marker);

	List<Trip> findTripList();

	void deleteTrip(String id);

	void updateTrip(Trip trip);

}
