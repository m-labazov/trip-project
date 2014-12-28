package ua.home.trip.api.service;

import ua.home.trip.data.Trip;

import java.util.List;


public interface ITripService extends ILinkService, IAbstractService<Trip> {

    List<Trip> findList();

}
