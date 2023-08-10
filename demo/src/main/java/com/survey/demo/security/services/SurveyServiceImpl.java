package com.survey.demo.security.services;

import com.survey.demo.models.surveys.Category;
import com.survey.demo.models.surveys.Survey;
import com.survey.demo.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SurveyServiceImpl implements  SurveyService{
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveySequenceGenerator surveySequenceGenerator;

    @Override
    public Survey addSurvey(Survey survey) {

        survey.setSId(surveySequenceGenerator.getSequenceNumber((Survey.SEQUENCE_NAME)));
        return this.surveyRepository.save(survey);
    }

    @Override
    public Survey updateSurvey(Survey survey) {
        return this.surveyRepository.save(survey);
    }

    @Override
    public Set<Survey> getSurveys() {
        return new HashSet<>(this.surveyRepository.findAll());
    }

    @Override
    public Survey getSurvey(int surveyId) {
        return this.surveyRepository.findById(surveyId).get();
    }

    @Override
    public void deleteSurvey(int surveyId) {
//        Survey survey = new Survey();
//        survey.setSId(surveyId);

        //this.surveyRepository.delete(survey);
        this.surveyRepository.deleteById(surveyId);
    }
}
