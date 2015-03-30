package ua.home.trip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.home.trip.api.data.ILocation;
import ua.home.trip.api.repository.ILocationRepository;
import ua.home.trip.api.service.ILocationService;

@Service
public class LocationService extends AbstractService<ILocation, ILocationRepository> implements ILocationService {

	@Autowired
	private ILocationRepository repository;

	@Override
	protected ILocationRepository getRepository() {
		return repository;
	}

}
