package ua.home.trip.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import ua.home.trip.api.repository.ITripRepository;
import ua.home.trip.data.Link;
import ua.home.trip.data.Trip;

@Repository
public class TripRepository implements ITripRepository {

	@Autowired
	private MongoTemplate template;

	@Override
	public void addLink(String tripId, Link link) {
		Update update = new Update().addToSet("links", link);
		template.updateFirst(Query.query(Criteria.where("id").is(tripId)),
				update, Trip.class);
	}

	@Override
	public Trip getTrip(String tripName) {
		return template.findOne(
				Query.query(Criteria.where("name").is(tripName)), Trip.class);
	}

	@Override
	public void insert(Trip trip) {
		template.insert(trip);
	}

	@Override
	public void deleteLink(String tripId, String linkName) {
		Query query = Query.query(Criteria.where("id").is(tripId));
		Trip trip = template.findOne(query, Trip.class);
		// TODO remove this
		Link linkToRemove = null;
		for (Link link : trip.getLinks()) {
			if (link.getName().equals(linkName)) {
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

		Update update = new Update().set("links.$", link);
		template.updateFirst(query, update, Trip.class);
	}
}
