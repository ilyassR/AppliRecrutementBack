package com.cgi.appliRecrutement.repositoryCustom;

import java.util.List;

import com.cgi.appliRecrutement.domain.Role;
import com.cgi.appliRecrutement.domain.User;

public interface UserRepositoryCustom {
	public List<User> getUsersByRole(Role role);
}
