package com.cgi.appliRecrutement.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.appliRecrutement.domain.Role;
import com.cgi.appliRecrutement.domain.RoleName;
import com.cgi.appliRecrutement.repository.RoleRepository;
import com.cgi.appliRecrutement.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public Optional<Role> findRoleByName(RoleName roleName) {
		return roleRepository.findByName(roleName);
	}

}
