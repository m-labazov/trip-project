package ua.home.trip.api.service;


public interface IAbstractService<T> {

    void insert(T entity);

    void update(T entity);

    void delete(String id);

}
