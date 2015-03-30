package ua.home.trip.repository;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ua.home.trip.api.data.ILocation;
import ua.home.trip.api.repository.ILocationRepository;
import ua.home.trip.data.Location;

@Repository
public class LocationRepository extends AbstractRepository<ILocation> implements ILocationRepository {

	@Override
	public void update(ILocation entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<Location> getEntityClass() {
		return Location.class;
	}

	@Override
	public List<? extends ILocation> findList(String tripId) {
		Query query = Query.query(Criteria.where("tripId").is(tripId));
		return template.find(query, getEntityClass());
	}

}
