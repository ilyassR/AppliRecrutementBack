package com.cgi.appliRecrutement.service;

import java.util.List;

import com.cgi.appliRecrutement.domain.Role;
import com.cgi.appliRecrutement.domain.User;

public interface UserService {
	
	public User saveUser(User user);
	
	public List<User> getUsersByRole(Role role);
	
}
