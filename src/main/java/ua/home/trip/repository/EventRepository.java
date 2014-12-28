package ua.home.trip.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ua.home.trip.api.repository.IEventRepository;
import ua.home.trip.data.Event;

import java.util.List;

@Repository
public class EventRepository extends AbstractRepository<Event> implements IEventRepository {

    @Override
    public List<Event> findEvents(String tripId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("tripId").is(tripId)).with(new Sort(Sort.DEFAULT_DIRECTION, "startTime"));
        return template.find(query, Event.class);
    }

    @Override
    public void update(Event entity) {
        throw new UnsupportedOperationException();
    }

}
