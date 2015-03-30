package ua.home.trip.api.repository;

import java.util.List;

import ua.home.trip.api.data.ITrip;
import ua.home.trip.data.Link;
import ua.home.trip.data.Marker;
import ua.home.trip.data.filter.Filter;

public interface ITripRepository extends IAbstractRepository<ITrip> {

	void addLink(String tripId, Link link);

	List<Link> getLinks(Filter filter);

	void deleteLink(String tripId, String linkName);

	void updateLink(String tripId, Link link);

	void addMarkerToLink(String tripId, String linkId, Marker marker);

	ITrip loadById(String id);

	void addMember(String id, String id2);

	List<Link> findEvents(String tripId);

	void expelMember(String tripId, String userId);

}
