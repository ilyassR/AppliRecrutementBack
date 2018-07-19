package com.cgi.appliRecrutement.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cgi.appliRecrutement.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPrincipal implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

    private String firstName;
    
    private String lastName;

    private String username;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String firstname, String lastname,  String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstname;
        this.lastName = lastname;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
    	List<GrantedAuthority> listAuthorities = new ArrayList<>();
    	listAuthorities.add(new SimpleGrantedAuthority( user.getRole().getName().name()));
        List<GrantedAuthority> authorities = listAuthorities;

        return new UserPrincipal(
                user.getId(),
                user.getPrenom(),
                user.getNom(),
                user.getPseudo(),
                user.getPassword(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

}
