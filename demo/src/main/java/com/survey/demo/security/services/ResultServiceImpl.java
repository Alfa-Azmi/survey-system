package com.survey.demo.security.services;

import com.survey.demo.models.User;
import com.survey.demo.models.surveys.Category;
import com.survey.demo.models.surveys.Result;
import com.survey.demo.models.surveys.Survey;
import com.survey.demo.repository.ResultRepository;
import com.survey.demo.repository.SurveyRepository;
import com.survey.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ResultServiceImpl implements ResultService{

    @Autowired
    private ResultRepository resultrepository;

    @Autowired
    private ResultSequenceGenerator resultSequenceGenerator;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Result addResult(Result result) {
        result.setResultId(resultSequenceGenerator.getSequenceNumber((Result.SEQUENCE_NAME)));
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


//    @Override
//    public List<Result> getAllResult() {
//        return this.resultrepository.findAll();
//    }

//    @Override
//    public List<Result> getResultOfSurvey(Survey survey) {
//        return this.resultrepository.findBySurvey(survey);
//    }
//
//    @Override
//    public List<Result> getResultOfUser(User user) {
//        return this.resultrepository.findByUser(user);
//    }
//
//    @Override
//    public List<Result> getResultOfUserAndSurvey(Survey survey, User user) {
//        return this.resultrepository.findBySurveyAndUser(survey,user);
//    }
}
