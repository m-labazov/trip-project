package ua.home.trip.api.service;

import ua.home.trip.data.Event;

import java.util.List;

public interface IEventService extends IAbstractService<Event> {

    List<Event> findEvents(String tripId);

}
