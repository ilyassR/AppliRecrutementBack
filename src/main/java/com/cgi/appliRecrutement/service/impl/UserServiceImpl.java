package com.cgi.appliRecrutement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.appliRecrutement.domain.Role;
import com.cgi.appliRecrutement.domain.User;
import com.cgi.appliRecrutement.repository.UserRepository;
import com.cgi.appliRecrutement.repositoryCustom.UserRepositoryCustom;
import com.cgi.appliRecrutement.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getUsersByRole(Role role) {
		return userRepository.getUsersByRole(role);
	}
	
	
	

}
