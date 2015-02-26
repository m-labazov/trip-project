package ua.home.trip.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Reference;
import org.springframework.stereotype.Repository;

import ua.home.trip.api.data.IUser;
import ua.home.trip.api.service.IUserRepository;
import ua.home.trip.data.User;
import ua.home.trip.util.UserFactory;

@Repository
public class UserRepository implements IUserRepository, ConnectionSignUp, InitializingBean {

	@Autowired
	private MongoTemplate template;
	@Resource(name = "usersConnectionRepository")
	private UsersConnectionRepository userConnectionRepository;

	@Override
	public IUser loadUserByUsername(String userName) {
		return loadUserById(userName);
	}

	@Override
	public IUser loadUserById(String userId) {
		Query query = new Query(Criteria.where("id").is(userId));
		return template.findOne(query, User.class);
	}

	@Override
	public List<IUser> loadContactList(String userId) {
		IUser loadUserById = loadUserById(userId);
		ConnectionRepository connectionRepository = userConnectionRepository.createConnectionRepository(userId);
		List<Connection<?>> connections = connectionRepository.findConnections(loadUserById.getProviderId());
		Set<IUser> result = new HashSet<>();
		// TODO fix it, when Connection repository is implemented
		// for (Connection<?> connection : connections) {
		result.addAll(getFriendUsers((Facebook) connections.get(0).getApi()));
		// }
		return new ArrayList<IUser>(result);
	}

	private List<IUser> getFriendUsers(Facebook api) {
		List<IUser> result = new ArrayList<>();
		PagedList<Reference> friends = api.friendOperations().getFriends();
		for (Reference reference : friends) {
			result.add(loadUserBySocialId("facebook", reference.getId()));
		}
		return result;
	}

	@Override
	public byte[] getUserProfileImage(String userId) {
		IUser loadUserById = loadUserById(userId);
		ConnectionRepository connectionRepository = userConnectionRepository.createConnectionRepository(userId);
		List<Connection<?>> connections = connectionRepository.findConnections(loadUserById.getProviderId());
		return ((Facebook) connections.get(0).getApi()).userOperations().getUserProfileImage();
	}

	@Override
	public List<IUser> loadUsersByIds(List<String> members) {
		Query query = Query.query(Criteria.where("id").in(members));
		List<User> users = template.find(query, User.class);
		List<IUser> result = new ArrayList<>();
		users.forEach(user -> result.add(user));
		return result;
	}

	@Override
	public IUser loadUserBySocialId(String providerId, String socialUserId) {
		Set<String> providerUserIds = new HashSet<>();
		providerUserIds.add(socialUserId);
		// TODO uncomment when connection persisting is implemented
		// Set<String> userId =
		// userConnectionRepository.findUserIdsConnectedTo(providerId,
		// providerUserIds);
		// Query query = Query.query(Criteria.where("id").is(userId));
		Query query = Query.query(Criteria.where("providerUserId").is(socialUserId));
		return template.findOne(query, User.class);
	}

	@Override
	public String execute(Connection<?> connection) {
		UserProfile userProfile = connection.fetchUserProfile();
		ConnectionKey key = connection.getKey();
		IUser user = loadUserBySocialId(key.getProviderId(), connection.getKey().getProviderUserId());
		if (user == null) {
			user = UserFactory.createUser(userProfile, connection.getKey());
			template.insert(user);
		}
		return user.getId();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		((InMemoryUsersConnectionRepository) userConnectionRepository).setConnectionSignUp(this);
	}

}
