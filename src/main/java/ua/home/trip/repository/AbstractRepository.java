package ua.home.trip.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import ua.home.trip.api.repository.IAbstractRepository;

public abstract class AbstractRepository<T> implements IAbstractRepository<T> {

    @Autowired
    protected MongoTemplate template;

    @Override
    public void insert(T entity) {
        template.insert(entity);
    }

    @Override
    public void delete(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
		template.remove(query, getEntityClass());
    }

	@Override
	public T loadById(String id) {
		Query query = Query.query(Criteria.where("id").is(id));
		return template.findOne(query, getEntityClass());
	}

	public abstract Class<T> getEntityClass();

}
