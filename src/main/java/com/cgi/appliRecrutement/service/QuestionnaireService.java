package com.cgi.appliRecrutement.service;

import java.util.List;

import com.cgi.appliRecrutement.domain.Questionnaire;

public interface QuestionnaireService {

	public List<Questionnaire> getAllQuestionnaire();
	public Questionnaire saveQCM(Questionnaire qcm);
	
}
