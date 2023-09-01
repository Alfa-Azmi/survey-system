package com.survey.demo.security.services.Impl;

import com.survey.demo.exceptions.CategoryNotFoundException;
import com.survey.demo.exceptions.SurveyNotFoundException;
import com.survey.demo.models.surveys.Category;
import com.survey.demo.models.surveys.Survey;
import com.survey.demo.repository.ResultRepository;
import com.survey.demo.repository.SurveyRepository;
import com.survey.demo.security.services.SequenceGeneratorService;
import com.survey.demo.security.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Override
    public Survey addSurvey(Survey survey) {

        survey.setSId(sequenceGeneratorService.getSequenceNumber((Survey.SEQUENCE_NAME)));
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
    public Survey getSurvey(int surveyId) throws SurveyNotFoundException {
        return this.surveyRepository.findById(surveyId).orElseThrow(() -> new SurveyNotFoundException("Survey Not Found with surveyId: " + surveyId));
    }

    @Override
    public void deleteSurvey(int surveyId) {

        this.surveyRepository.deleteById(surveyId);
    }

    @Override
    public List<Survey> getSurveysOfCategory(Category category) {
        return this.surveyRepository.findBycategory(category);
    }

    //get Active Surveys
    @Override
    public List<Survey> getActiveSurveys() {
        return this.surveyRepository.findByActive(true);
    }

    @Override
    public List<Survey> getActiveSurveysOfCategory(Category c) {
        return this.surveyRepository.findByCategoryAndActive(c,true);
    }

    @Override
    public List<Survey> searchSurvey(String keyword) {
        return this.surveyRepository.findByTitleContainingIgnoreCase(keyword);

    }

}
