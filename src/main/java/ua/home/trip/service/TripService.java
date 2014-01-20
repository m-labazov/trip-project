package ua.home.trip.service;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.home.trip.api.repository.ITripRepository;
import ua.home.trip.api.service.ITripService;
import ua.home.trip.data.Link;
import ua.home.trip.data.Trip;

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
	public Trip findTrip(String tripName) {
		return tripRepository.getTrip(tripName);
	}

	@Override
	public void insert(Trip trip) {
		if (StringUtils.isBlank(trip.getId())) {
			trip.setId(UUID.randomUUID().toString());
		}
		tripRepository.insert(trip);
	}

	@Override
	public void deleteLink(String tripId, String linkName) {
		tripRepository.deleteLink(tripId, linkName);
	}

	@Override
	public void updateLink(String tripId, Link link) {
		tripRepository.updateLink(tripId, link);
	}

}
