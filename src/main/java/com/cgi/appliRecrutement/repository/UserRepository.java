package com.cgi.appliRecrutement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.appliRecrutement.domain.User;
import com.cgi.appliRecrutement.repositoryCustom.UserRepositoryCustom;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

	Optional<User> findByPseudo(String pseudo);
	
	Boolean existsByPseudo(String pseudo);

}
