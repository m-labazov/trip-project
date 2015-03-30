package ua.home.trip.api.repository;

import java.util.List;


public interface IAbstractRepository<T> {

    void insert(T entity);
    void update(T entity);
    void delete(String id);
	T loadById(String id);
	List<? extends T> findList(String id);
}
