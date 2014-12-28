package ua.home.trip.repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.util.MultiValueMap;

import java.util.List;

public class ConnectionRepositoryImpl implements ConnectionRepository {

    private String userId;
    private MongoTemplate template;

    public ConnectionRepositoryImpl(String userId, MongoTemplate template) {
        this.userId = userId;
        this.template = template;
    }

    @Override
    public MultiValueMap<String, Connection<?>> findAllConnections() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Connection<?>> findConnections(String providerId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <A> List<Connection<A>> findConnections(Class<A> apiType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MultiValueMap<String, Connection<?>> findConnectionsToUsers(MultiValueMap<String, String> providerUserIds) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Connection<?> getConnection(ConnectionKey connectionKey) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <A> Connection<A> getConnection(Class<A> apiType, String providerUserId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <A> Connection<A> getPrimaryConnection(Class<A> apiType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <A> Connection<A> findPrimaryConnection(Class<A> apiType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addConnection(Connection<?> connection) {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateConnection(Connection<?> connection) {
        // TODO Auto-generated method stub
        // connection.
    }

    @Override
    public void removeConnections(String providerId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void removeConnection(ConnectionKey connectionKey) {
        // TODO Auto-generated method stub

    }

}
