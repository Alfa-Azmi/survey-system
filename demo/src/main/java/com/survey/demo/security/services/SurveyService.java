package com.survey.demo.security.services;

import com.survey.demo.models.surveys.Survey;

import java.util.Set;

public interface SurveyService {

    public Survey addSurvey(Survey survey);
    public Survey updateSurvey(Survey survey);

    public Set<Survey> getSurveys();
    public Survey getSurvey(String surveyId);
    public void deleteSurvey(String surveyId);
}
