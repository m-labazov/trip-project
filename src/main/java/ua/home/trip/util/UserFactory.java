package ua.home.trip.util;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;

import ua.home.trip.api.data.IUser;
import ua.home.trip.data.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class UserFactory {

    public static IUser createUser(UserProfile userProfile, ConnectionKey connectionKey) {
        IUser result = new User();
        result.setId(UUID.randomUUID().toString());
        result.setEmail(userProfile.getEmail());
        result.setFirstName(userProfile.getFirstName());
        result.setLastName(userProfile.getLastName());
        result.setName(userProfile.getName());
        result.setUsername(userProfile.getUsername());
        result.setProviderId(connectionKey.getProviderId());
        result.setProviderUserId(connectionKey.getProviderUserId());
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        result.setAuthorities(authorities);
        return result;
    }

}
