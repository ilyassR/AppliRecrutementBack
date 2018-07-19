package com.cgi.appliRecrutement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgi.appliRecrutement.domain.Candidat;

@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long> {
	
}
