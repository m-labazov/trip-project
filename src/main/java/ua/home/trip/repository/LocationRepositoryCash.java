package ua.home.trip.repository;

import ua.home.trip.api.repository.ILocationRepository;
import ua.home.trip.controller.Locations;

//@Repository
public class LocationRepositoryCash implements ILocationRepository {

	private Locations locations;

	@Override
	public void insert(Locations locations) {
		this.locations = locations;

	}

	@Override
	public Locations get(String id) {
		return locations;
	}

}
