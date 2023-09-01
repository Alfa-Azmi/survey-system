package com.survey.demo.security.services.Impl;

import com.survey.demo.models.User;
import com.survey.demo.models.surveys.Category;
import com.survey.demo.models.surveys.Result;
import com.survey.demo.models.surveys.Survey;
import com.survey.demo.repository.ResultRepository;
import com.survey.demo.repository.SurveyRepository;
import com.survey.demo.repository.UserRepository;
import com.survey.demo.security.services.ResultService;
import com.survey.demo.security.services.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultrepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Result addResult(Result result) {
        result.setResultId(sequenceGeneratorService.getSequenceNumber((Result.SEQUENCE_NAME)));
        return this.resultrepository.save(result);
    }

    @Override
    public List<Result> getBySurveyID(int surveyID) {
            List<Result> results = resultrepository.findBySurveyID(surveyID);
            return results;

    }

    @Override
    public List<Result> getByUserIDAndSurveyID(int userID, int surveyID) {
        List<Result>  results= resultrepository.findByUserIDAndSurveyID(userID,surveyID);
        return results;
    }

    @Override
    public List<Result> getByUserID( int userID) {
        List<Result> results = resultrepository.findByUserID(userID);
        return results;
    }
}
