package ua.home.trip.api.service;

import java.util.List;

import ua.home.trip.api.data.IIdentifable;

public interface IAbstractService<T extends IIdentifable> {

    void insert(T entity);

    void update(T entity);

    void delete(String id);

	T loadById(String id);

	List<? extends T> findList(String id);

}
