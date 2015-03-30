package ua.home.trip.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ua.home.trip.api.data.IUser;
import ua.home.trip.api.repository.IUserRepository;
import ua.home.trip.api.service.IUserService;

@Service
public class UserService implements IUserService {

    @Resource
    private IUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        IUser user = repository.loadUserByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + userName);
        }
        return user;
    }

    @Override
	public List<IUser> loadContactList() {
        IUser user = (IUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<IUser> result = repository.loadContactList(user.getId());
        return result;
    }


    /**
     * @param repository the repository to set
     */
    public void setRepository(IUserRepository repository) {
        this.repository = repository;
    }

	@Override
	public List<IUser> loadUsersByIds(List<String> members) {
		return repository.loadUsersByIds(members);
	}

	@Override
	public IUser loadUserBySocialId(String providerId, String providerUserId) {
		return repository.loadUserBySocialId(providerId, providerUserId);
	}

	@Override
	public byte[] getUserProfileImage(String userId) {
		return repository.getUserProfileImage(userId);
	}

}
