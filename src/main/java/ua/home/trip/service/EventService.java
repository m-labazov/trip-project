package ua.home.trip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.home.trip.api.repository.IEventRepository;
import ua.home.trip.api.service.IEventService;
import ua.home.trip.data.Event;

import java.util.List;

@Service
public class EventService extends AbstractService<Event, IEventRepository> implements IEventService {

    @Autowired
    private IEventRepository repository;

    @Override
    public List<Event> findEvents(String tripId) {
        return repository.findEvents(tripId);
    }

    @Override
    protected IEventRepository getRepository() {
        return repository;
    }

}
