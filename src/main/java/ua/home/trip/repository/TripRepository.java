package ua.home.trip.repository;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.UnwindOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import ua.home.trip.api.repository.ITripRepository;
import ua.home.trip.data.Link;
import ua.home.trip.data.Marker;
import ua.home.trip.data.Trip;
import ua.home.trip.data.filter.Filter;

@Repository
public class TripRepository extends AbstractRepository<Trip> implements ITripRepository {

    private static Logger LOGGER = Logger.getLogger(TripRepository.class);

    @Override
    public void addLink(String tripId, Link link) {
        Update update = new Update().addToSet("links", link);
        template.updateFirst(Query.query(Criteria.where("id").is(tripId)), update, Trip.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Link> getLinks(Filter filter) {
        Criteria idCriteria = Criteria.where("_id").is(filter.getId());

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
    public void deleteLink(String tripId, String linkId) {
        Query query = Query.query(Criteria.where("id").is(tripId));
        Trip trip = template.findOne(query, Trip.class);
		Link linkToRemove = trip.getLinks().stream()
				.filter((link) -> link.getLinkId().equals(linkId)).findFirst()
				.get();

        Update update = new Update().pull("links", linkToRemove);
        template.updateFirst(query, update, Trip.class);
    }

    @Override
    public void updateLink(String tripId, Link link) {
        Query query = Query.query(Criteria.where("id").is(tripId).and("links")
                .elemMatch(Criteria.where("linkId").is(link.getLinkId())));

        Update update = new Update().set("links.$.name", link.getName()).set("links.$.type", link.getType())
                .set("links.$.url", link.getUrl()).set("links.$.location", link.getLocation());
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
	public void addMember(String tripId, String userId) {
		Query query = Query.query(Criteria.where("id").is(tripId));

		Update update = new Update().addToSet("members", userId);
		template.updateFirst(query, update, Trip.class);
	}

    @Override
    public void update(Trip trip) {
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

    @Override
    public List<Trip> findList(String creator) {
        ProjectionOperation projection = Aggregation.project("id", "name", "startDate", "endDate", "comment",
                "creator", "members");
        UnwindOperation unwind = Aggregation.unwind("members");
        MatchOperation match = Aggregation.match(Criteria.where("members").is(creator));
        Aggregation aggregation = Aggregation.newAggregation(projection, unwind, match);
        return template.aggregate(aggregation, Trip.class, Trip.class).getMappedResults();
    }

	@Override
	public Class<Trip> getEntityClass() {
		return Trip.class;
	}

}
