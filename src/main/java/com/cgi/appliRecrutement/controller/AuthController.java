package com.cgi.appliRecrutement.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cgi.appliRecrutement.config.JwtTokenProvider;
import com.cgi.appliRecrutement.domain.Role;
import com.cgi.appliRecrutement.domain.RoleName;
import com.cgi.appliRecrutement.domain.User;
import com.cgi.appliRecrutement.exception.AppException;
import com.cgi.appliRecrutement.payload.ApiResponse;
import com.cgi.appliRecrutement.payload.JwtAuthenticationResponse;
import com.cgi.appliRecrutement.payload.LoginRequest;
import com.cgi.appliRecrutement.payload.SingUpRequest;
import com.cgi.appliRecrutement.repository.RoleRepository;
import com.cgi.appliRecrutement.repository.UserRepository;

/**
 * @author ilyass.rahmoune
 * Authentication Controller
 */
@RestController
@RequestMapping("/authenticate")
public class AuthController {
	public static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider tokenProvider;
	
	@PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println("Sign In");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }
	
	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SingUpRequest signUpRequest) {
		System.out.println("Sign Up");
        if(userRepository.existsByPseudo(signUpRequest.getUsername())) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        
        // Creating user's account
        User user = new User(signUpRequest.getUsername(), signUpRequest.getPassword(), signUpRequest.getFisrtname(), signUpRequest.getLastname());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRole(userRole);

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getPseudo()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

}
