package ua.home.trip.api.repository;

import ua.home.trip.controller.Locations;

public interface ILocationRepository {

	void insert(Locations locations);

	Locations get(String id);

}
