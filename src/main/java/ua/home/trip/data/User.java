package ua.home.trip.data;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import ua.home.trip.api.data.IUser;

public class User extends Identifier implements IUser {

    private static final long serialVersionUID = 4549235662149221505L;
    private String username;
    // private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String name;
    private String providerId;
    private String providerUserId;
    private Collection<GrantedAuthority> authorities;

    /**
     * @return the email
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUserId() {
		return getId();
    }

    /**
     * @return the firstName
     */
    @Override
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    @Override
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param username the username to set
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getProviderId() {
        return providerId;
    }

    /**
     * @param providerId the providerId to set
     */
    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    /**
     * @return the providerUserId
     */
    @Override
    public String getProviderUserId() {
        return providerUserId;
    }

    /**
     * @param providerUserId the providerUserId to set
     */
    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    /**
     * @param authorities the authorities to set
     */
    @Override
    public void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

}
