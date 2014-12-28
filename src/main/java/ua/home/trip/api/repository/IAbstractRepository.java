package ua.home.trip.api.repository;


public interface IAbstractRepository<T> {

    void insert(T entity);
    void update(T entity);
    void delete(String id);

}
