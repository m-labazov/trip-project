package ua.home.trip.api.repository;

import ua.home.trip.data.Event;

import java.util.List;

public interface IEventRepository extends IAbstractRepository<Event> {

    List<Event> findEvents(String tripId);

}
