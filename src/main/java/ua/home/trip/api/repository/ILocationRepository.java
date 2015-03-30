package ua.home.trip.api.repository;

import java.util.List;

import ua.home.trip.api.data.ILocation;

public interface ILocationRepository extends IAbstractRepository<ILocation> {

	List<? extends ILocation> findList(String tripId);

}
