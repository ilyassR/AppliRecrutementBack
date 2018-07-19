package com.cgi.appliRecrutement.controller;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cgi.appliRecrutement.domain.Candidat;
import com.cgi.appliRecrutement.domain.Role;
import com.cgi.appliRecrutement.domain.RoleName;
import com.cgi.appliRecrutement.domain.User;
import com.cgi.appliRecrutement.exception.AppException;
import com.cgi.appliRecrutement.payload.ApiResponse;
import com.cgi.appliRecrutement.repository.CandidatRepository;
import com.cgi.appliRecrutement.repository.UserRepository;
import com.cgi.appliRecrutement.service.CandidatService;
import com.cgi.appliRecrutement.service.RoleService;
import com.cgi.appliRecrutement.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	CandidatService candidatService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	
	@GetMapping("/allcandidats")
	public List<User> retrieveAllCandidats() {
		Role roleCandidat = roleService.findRoleByName(RoleName.ROLE_CANDIDAT).
				orElseThrow(() -> new AppException("User Role not set."));
		List<User> candidats  = userService.getUsersByRole(roleCandidat);
		return candidats;
	}
	
	@PostMapping("/createCandidat")
	public ResponseEntity<?> createCandidat(@Valid @RequestBody User candidat) {
		System.out.println(candidat);
		
		candidat.setPassword(passwordEncoder.encode(candidat.getPassword()));
		
		Role candidatRole = roleService.findRoleByName(RoleName.ROLE_CANDIDAT)
				.orElseThrow(() -> new AppException("User Role not set."));
		
		candidat.setRole(candidatRole);
		
		User result = userRepository.save(candidat);
		
		URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/candidats/{pseudo}")
                .buildAndExpand(result.getPseudo()).toUri();
		return ResponseEntity.created(location).body(new ApiResponse(true, "Candidat  enregistré avec succés"));
	}
	
}
