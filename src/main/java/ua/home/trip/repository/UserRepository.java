package ua.home.trip.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;

import ua.home.trip.api.data.IUser;
import ua.home.trip.api.service.IUserRepository;
import ua.home.trip.data.User;
import ua.home.trip.util.UserFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class UserRepository implements UsersConnectionRepository, IUserRepository {

    @Autowired
    private MongoTemplate template;

    @Override
    public List<String> findUserIdsWithConnection(Connection<?> connection) {
        ConnectionKey key = connection.getKey();
        List<IUser> userSocialConnectionList = findByProviderIdAndProviderUserId(key.getProviderId(),
                key.getProviderUserId());
        List<String> localUserIds = new ArrayList<String>();
        for (IUser userSocialConnection : userSocialConnectionList) {
            localUserIds.add(userSocialConnection.getUserId());
        }
        if (localUserIds.size() == 0) {
            localUserIds.add(signUpUser(connection));
        }
        return localUserIds;
    }

    private String signUpUser(Connection<?> connection) {
        IUser createUser = UserFactory.createUser(connection.fetchUserProfile(), connection.getKey());
        template.insert(createUser);
        return createUser.getId();

    }

    private List<IUser> findByProviderIdAndProviderUserId(String providerId, String providerUserId) {
        Query query = new Query(Criteria.where("providerId").is(providerId)
                .andOperator(Criteria.where("providerUserId").is(providerUserId)));
        List<IUser> result = new LinkedList<IUser>();
        for (User user : template.find(query, User.class)) {
            result.add(user);
        }
        return result;
    }

    public Set<String> findUserIdsConnectedTo(String providerId, Set<String> providerUserIds) {
        final Set<String> localUserIds = new HashSet<String>();
        List<IUser> userSocialConnectionList = findByProviderIdAndProviderUserIdIn(providerId, providerUserIds);
        for (IUser userSocialConnection : userSocialConnectionList) {
            localUserIds.add(userSocialConnection.getUserId());
        }
        return localUserIds;
    }

    private List<IUser> findByProviderIdAndProviderUserIdIn(String providerId, Set<String> providerUserIds) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ConnectionRepository createConnectionRepository(String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("userId cannot be null");
        }
        return new ConnectionRepositoryImpl(userId, template);
    }

    @Override
    public IUser loadUserByUsername(String userName) {
        return loadUserById(userName);
    }

    @Override
    public IUser loadUserById(String userId) {
        Query query = new Query(Criteria.where("email").is(userId));
        return template.findOne(query, User.class);
    }

}
