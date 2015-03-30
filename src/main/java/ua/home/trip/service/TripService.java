package ua.home.trip.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ua.home.trip.api.data.ITrip;
import ua.home.trip.api.data.IUser;
import ua.home.trip.api.repository.ITripRepository;
import ua.home.trip.api.service.ITripService;
import ua.home.trip.api.service.IUserService;
import ua.home.trip.data.Link;
import ua.home.trip.data.Marker;
import ua.home.trip.data.filter.Filter;

@Service
public class TripService extends AbstractService<ITrip, ITripRepository> implements ITripService {

	@Autowired
	private ITripRepository tripRepository;
    @Autowired
    private IUserService userService;

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
    protected ITripRepository getRepository() {
        return tripRepository;
    }

    @Override
	public void insert(ITrip entity) {
        IUser user = (IUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        entity.setCreator(user.getId());
        entity.getMembers().add(user.getId());
        super.insert(entity);
    }

    @Override
	public List<IUser> loadTripMembers(String id) {
		ITrip trip = getRepository().loadById(id);
        List<IUser> users = userService.loadUsersByIds(trip.getMembers());
		return users;
    }

	@Override
	public void addTripMember(String tripId, String userId) {
		tripRepository.addMember(tripId, userId);
	}

	@Override
	public List<IUser> loadNewMembers(String tripId, List<IUser> contactList) {
		ITrip trip = loadById(tripId);
		List<IUser> result = contactList.stream()
				.filter(user -> !trip.getMembers().contains(user.getId()))
				.collect(Collectors.toList());
		return result;
	}

	@Override
	public List<Link> findEvents(String tripId) {
		return getRepository().findEvents(tripId);
	}

	@Override
	public void expelTripMember(String tripId, String userId) {
		tripRepository.expelMember(tripId, userId);
	}

}
