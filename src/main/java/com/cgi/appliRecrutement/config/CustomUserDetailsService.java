package com.cgi.appliRecrutement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgi.appliRecrutement.domain.User;
import com.cgi.appliRecrutement.repository.UserRepository;
import com.cgi.appliRecrutement.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
    UserRepository userRepository;
	@Autowired
	UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        // Let people login with either username
        User user = userRepository.findByPseudo(username)
                .orElseThrow(() -> 
                        new UsernameNotFoundException("User not found with username or email : " + username)
        );
        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + id)
        );
        return UserPrincipal.create(user);
    }

}
