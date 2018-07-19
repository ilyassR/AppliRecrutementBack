package com.cgi.appliRecrutement.service;

import java.util.Optional;

import com.cgi.appliRecrutement.domain.Role;
import com.cgi.appliRecrutement.domain.RoleName;

public interface RoleService {

	public Optional<Role> findRoleByName(RoleName name);
	
}
