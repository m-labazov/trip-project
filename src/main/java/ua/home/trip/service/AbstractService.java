package ua.home.trip.service;

import org.apache.commons.lang3.StringUtils;

import ua.home.trip.api.repository.IAbstractRepository;
import ua.home.trip.api.service.IAbstractService;
import ua.home.trip.data.Identifier;

import java.util.UUID;

public abstract class AbstractService<T extends Identifier, R extends IAbstractRepository<T>> implements
        IAbstractService<T> {

    protected abstract R getRepository();

    @Override
    public void insert(T entity) {
        if (StringUtils.isBlank(entity.getId())) {
            entity.setId(UUID.randomUUID().toString());
        }
        getRepository().insert(entity);
    }

    @Override
    public void delete(String id) {
        getRepository().delete(id);
    }

    @Override
    public void update(T entity) {
        getRepository().update(entity);
    }
}
