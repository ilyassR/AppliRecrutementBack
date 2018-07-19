package com.cgi.appliRecrutement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgi.appliRecrutement.domain.Questionnaire;
import com.cgi.appliRecrutement.repository.QuestionnaireRepository;
import com.cgi.appliRecrutement.service.QuestionnaireService;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
	
	@Autowired
	private QuestionnaireRepository questionnaireRepository;
	
	@Override
	public List<Questionnaire> getAllQuestionnaire() {
		return questionnaireRepository.findAll();
	}

	@Override
	public Questionnaire saveQCM(Questionnaire qcm) {
		return questionnaireRepository.save(qcm);
	}
	
	
}
