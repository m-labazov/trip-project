package ua.home.trip.api.repository;

import java.util.List;

import ua.home.trip.data.Link;
import ua.home.trip.data.Marker;
import ua.home.trip.data.Trip;
import ua.home.trip.data.filter.Filter;

public interface ITripRepository extends IAbstractRepository<Trip> {

	void addLink(String tripId, Link link);

	List<Link> getLinks(Filter filter);

	void deleteLink(String tripId, String linkName);

	void updateLink(String tripId, Link link);

	void addMarkerToLink(String tripId, String linkId, Marker marker);

    List<Trip> findList(String creator);

    Trip loadById(String id);

	void addMember(String id, String id2);

}
