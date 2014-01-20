package ua.home.trip.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ua.home.trip.api.repository.ILocationRepository;
import ua.home.trip.controller.Locations;

@Repository
public class LocationRepositoryMongo implements ILocationRepository {

	@Autowired
	private MongoTemplate template;

	@Override
	public void insert(Locations locations) {
		template.insert(locations);
	}

	@Override
	public Locations get(String id) {
		return template.findOne(new Query(), Locations.class);
	}

}
