package ua.home.trip.api.service;

import java.util.List;

import ua.home.trip.api.data.ILocation;

public interface ILocationService extends IAbstractService<ILocation> {

	List<? extends ILocation> findList(String tripId);

}
