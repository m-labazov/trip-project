package ua.home.trip.api.service;

import ua.home.trip.controller.Locations;

public interface ILocationService {

	void insert(Locations locations);

	Locations get(String id);

}
