package ua.home.trip.service;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.home.trip.api.repository.ITripRepository;
import ua.home.trip.api.service.ITripService;
import ua.home.trip.data.Link;
import ua.home.trip.data.Marker;
import ua.home.trip.data.Trip;
import ua.home.trip.data.filter.Filter;

@Service
public class TripService implements ITripService {

	@Autowired
	private ITripRepository tripRepository;

	@Override
	public void addLink(String tripId, Link link) {
		if (StringUtils.isBlank(link.getLinkId())) {
			link.setLinkId(UUID.randomUUID().toString());
		}
		tripRepository.addLink(tripId, link);
	}

	@Override
	public List<Link> findLinks(Filter filter) {
		return tripRepository.getLinks(filter);
	}

	@Override
	public void insert(Trip trip) {
		if (StringUtils.isBlank(trip.getId())) {
			trip.setId(UUID.randomUUID().toString());
		}
		tripRepository.insert(trip);
	}

	@Override
	public void deleteLink(String tripId, String linkId) {
		tripRepository.deleteLink(tripId, linkId);
	}

	@Override
	public void updateLink(String tripId, Link link) {
		tripRepository.updateLink(tripId, link);
	}

	@Override
	public void addMarkerToLink(String tripId, String linkId, Marker marker) {
		tripRepository.addMarkerToLink(tripId, linkId, marker);
	}

	@Override
	public List<Trip> findTripList() {
		return tripRepository.findTripList();
	}

	@Override
	public void deleteTrip(String id) {
		tripRepository.deleteTrip(id);
	}

	@Override
	public void updateTrip(Trip trip) {
		tripRepository.updateTrip(trip);
	}

}
