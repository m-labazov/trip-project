package ua.home.trip.repository;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import ua.home.trip.api.repository.ITripRepository;
import ua.home.trip.data.Link;
import ua.home.trip.data.Marker;
import ua.home.trip.data.Trip;
import ua.home.trip.data.filter.Filter;

import java.util.Collections;
import java.util.List;

@Repository
public class TripRepository implements ITripRepository {

    private static Logger LOGGER = Logger.getLogger(TripRepository.class);

    @Autowired
    private MongoTemplate template;

    @Override
    public void addLink(String tripId, Link link) {
        Update update = new Update().addToSet("links", link);
        template.updateFirst(Query.query(Criteria.where("id").is(tripId)), update, Trip.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Link> getLinks(Filter filter) {
        Criteria idCriteria = Criteria.where("_id").is(filter.getId());

        LOGGER.info("Start!");
        MatchOperation typeMatchOperation = null;
        if (StringUtils.isNotBlank(filter.getType())) {
            typeMatchOperation = Aggregation.match(Criteria.where("links.type").is(filter.getType()));
        } else {
            typeMatchOperation = Aggregation.match(Criteria.where("links.type").ne(null));
        }
        Aggregation newAggregation = Aggregation.newAggregation(Aggregation.match(idCriteria),
                Aggregation.unwind("links"), typeMatchOperation, Aggregation.group("id").push("links").as("links"));
        Trip result = template.aggregate(newAggregation, Trip.class, Trip.class).getUniqueMappedResult();
        return result != null ? result.getLinks() : Collections.EMPTY_LIST;
    }

    @Override
    public void insert(Trip trip) {
        template.insert(trip);
    }

    @Override
    public void deleteLink(String tripId, String linkId) {
        Query query = Query.query(Criteria.where("id").is(tripId));
        Trip trip = template.findOne(query, Trip.class);
        // TODO remove this
        Link linkToRemove = null;
        for (Link link : trip.getLinks()) {
            if (link.getLinkId().equals(linkId)) {
                linkToRemove = link;
                break;
            }
        }

        Update update = new Update().pull("links", linkToRemove);
        template.updateFirst(query, update, Trip.class);
    }

    @Override
    public void updateLink(String tripId, Link link) {
        Query query = Query.query(Criteria.where("id").is(tripId).and("links")
                .elemMatch(Criteria.where("linkId").is(link.getLinkId())));

        Update update = new Update().set("links.$.name", link.getName()).set("links.$.type", link.getType())
                .set("links.$.url", link.getUrl());
        template.updateFirst(query, update, Trip.class);
    }

    @Override
    public void addMarkerToLink(String tripId, String linkId, Marker marker) {
        Query query = Query.query(Criteria.where("id").is(tripId).and("links")
                .elemMatch(Criteria.where("linkId").is(linkId)));

        Update update = new Update().set("links.$.marker", marker);
        template.updateFirst(query, update, Trip.class);
    }

    @Override
    public List<Trip> findTripList() {
        Query query = new Query();
        query.fields().include("_id").include("name").include("startDate").include("endDate").include("comment");
        return template.find(query, Trip.class);
    }

    @Override
    public void deleteTrip(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        template.remove(query, Trip.class);
    }

    @Override
    public void updateTrip(Trip trip) {
        Query query = Query.query(Criteria.where("id").is(trip.getId()));
        Update update = new Update();
        if (StringUtils.isNotBlank(trip.getName())) {
            update.set("name", trip.getName());
        }
        if (trip.getStartDate() != null) {
            update.set("startDate", trip.getStartDate());
        }
        if (trip.getEndDate() != null) {
            update.set("endDate", trip.getEndDate());
        }
        template.updateFirst(query, update, Trip.class);
    }
}
