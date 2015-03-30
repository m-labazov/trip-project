package ua.home.trip.repository;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ua.home.trip.api.data.IInformation;
import ua.home.trip.api.repository.IInformationRepository;
import ua.home.trip.data.Information;

@Repository
public class InformationRepository extends AbstractRepository<IInformation> implements IInformationRepository {

	@Override
	public void update(IInformation entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public Class<? extends IInformation> getEntityClass() {
		return Information.class;
	}

	@Override
	public List<? extends IInformation> findList(String id) {
		Query query = Query.query(Criteria.where("locationId").is(id));
		return template.find(query, getEntityClass());
	}

}
