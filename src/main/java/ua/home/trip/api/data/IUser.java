package ua.home.trip.api.data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.security.SocialUserDetails;

import java.util.Collection;

public interface IUser extends UserDetails, SocialUserDetails, IIdentifable {

    String getEmail();

    void setEmail(String email);

    String getFirstName();

    void setFirstName(String firstName);

    String getLastName();

    void setLastName(String lastName);

    String getName();

    void setName(String name);

    void setUsername(String username);

    void setProviderId(String providerId);

    void setProviderUserId(String providerUserId);

    void setAuthorities(Collection<GrantedAuthority> authorities);

}
