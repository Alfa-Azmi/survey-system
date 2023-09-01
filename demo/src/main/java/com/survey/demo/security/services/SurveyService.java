package com.survey.demo.security.services;

import com.survey.demo.models.surveys.Category;
import com.survey.demo.models.surveys.Survey;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface SurveyService {

    public Survey addSurvey(Survey survey);
    public Survey updateSurvey(Survey survey);

    public Set<Survey> getSurveys();
    public Survey getSurvey(int surveyId);
    public void deleteSurvey(int surveyId);
    public List<Survey> getSurveysOfCategory(Category category);
    public List<Survey> getActiveSurveys();
    public List<Survey> getActiveSurveysOfCategory(Category c);

    public List<Survey> searchSurvey(String keyword);



}
