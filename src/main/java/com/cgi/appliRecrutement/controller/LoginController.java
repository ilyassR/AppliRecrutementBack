package com.cgi.appliRecrutement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgi.appliRecrutement.config.CurrentUser;
import com.cgi.appliRecrutement.config.JwtTokenProvider;
import com.cgi.appliRecrutement.config.UserPrincipal;

@RestController
@RequestMapping("/home")
public class LoginController {
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@GetMapping("/user")
	public UserPrincipal homePageAfterLoging(@CurrentUser UserPrincipal currentUser) {
		 return currentUser;
	}

}
