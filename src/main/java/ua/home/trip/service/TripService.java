package ua.home.trip.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import ua.home.trip.api.data.IUser;
import ua.home.trip.api.repository.ITripRepository;
import ua.home.trip.api.service.ITripService;
import ua.home.trip.data.Link;
import ua.home.trip.data.Marker;
import ua.home.trip.data.Trip;
import ua.home.trip.data.filter.Filter;

import java.util.List;
import java.util.UUID;

@Service
public class TripService extends AbstractService<Trip, ITripRepository> implements ITripService {

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
    public void insert(Trip entity) {
        IUser user = (IUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        entity.setCreator(user.getId());
        super.insert(entity);
    }

    @Override
    public List<Trip> findList() {
        IUser user = (IUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getRepository().findList(user.getId());
    }

}
