package ua.home.trip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.home.trip.api.repository.ILocationRepository;
import ua.home.trip.api.service.ILocationService;
import ua.home.trip.controller.Locations;

@Service
public class LocationService implements ILocationService {

	@Autowired
	private ILocationRepository locationRepository;

	@Override
	public void insert(Locations locations) {
		locationRepository.insert(locations);
	}

	@Override
	public Locations get(String id) {
		return locationRepository.get(id);
	}

}
